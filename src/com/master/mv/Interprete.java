package com.master.mv;

import com.master.mv.comandos.Assignacion;
import com.master.mv.comandos.Comando;
import com.master.mv.comandos.Declaracion;
import com.master.mv.comandos.If;
import com.master.mv.comandos.Return;
import com.master.mv.comandos.Sequence;
import com.master.mv.comandos.Skip;
import com.master.mv.comandos.While;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.expresiones.BinOp;
import com.master.mv.expresiones.Constante;
import com.master.mv.expresiones.Constructor;
import com.master.mv.expresiones.Funcion;
import com.master.mv.expresiones.Metodo;
import com.master.mv.expresiones.Variable;
import com.master.mv.types.Booleano;
import com.master.mv.types.Referencia;
import com.master.mv.types.Tipo;
import com.master.mv.utils.Context;

public class Interprete extends Visitor {

	// Definicion del contexto basado en instancias
	// la definicion de las clases de la maquina
	// orientada a objetos.
	private Context context;

	public Interprete() {
		this.context = new Context();
	}

	@Override
	public Object visitaWhile(While w) throws EvalExceptions {
		try {
			for (;;) {
				Booleano v = (Booleano) w.e.visita(this);
				if (!v.b) {
					break;
				}
				w.c.visita(this);
			}
		} catch (ClassCastException e) {
			throw new EvalExceptions("While boolean value excepted!!");
		}
		return w;
	}

	@Override
	public Object visitaIf(If i) throws EvalExceptions {
		try {
			Booleano v = (Booleano) i.e.visita(this);

			if (v.b) {
				i.c1.visita(this);				
			}
			else {
				i.c2.visita(this);
			}
			
		} catch (ClassCastException e) {
			throw new EvalExceptions("if boolean value excepted!!");
		}
		return i;
	}

	@Override
	public Object visitaSeq(Sequence s) throws EvalExceptions {
		s.c1.visita(this);
		return s.c2.visita(this);
	}

	@Override
	public Object visitaAssign(Assignacion a) throws EvalExceptions {
		Tipo t = (Tipo) a.e.visita(this);
		this.context.update(a.id, t);
		return t;
	}

	@Override
	public Object visitaSkip(Skip s) throws EvalExceptions {
		return s;
	}

	@Override
	public Object visitaBinOp(BinOp b) throws EvalExceptions {
		Tipo v1 = (Tipo) b.e1.visita(this);
		Tipo v2 = (Tipo) b.e2.visita(this);
		return b.op.apply(v1, v2);
	}

	@Override
	public Object visitaVar(Variable v) throws EvalExceptions {
		return this.context.lookup(v.id);
	}

	@Override
	public Object visitaConst(Constante c) {
		return c.n;
	}

	@Override
	public Object visitaDeclare(Declaracion declare) throws EvalExceptions {
		this.context.insert(declare.id, declare.tipo);
		return declare;
	}

	@Override
	public Object visitaReturn(Return r) throws EvalExceptions {
		this.context.update(Context.RETURN, r.expr.eval(this.context));
		return r;
	}

	@Override
	public Object visitaFuncion(Funcion funcion) throws EvalExceptions {
		funcion.values = getValues(funcion);
		return funcion;
	}

	public Tipo[] getValues(Funcion f) throws EvalExceptions {
		if (f.argumentos.size() != f.parametros.size()) {
			throw new EvalExceptions("Bad call to funcion " + f.id);
		}

		f.values = new Tipo[f.argumentos.size()];
		for (int i = 0; i < f.argumentos.size(); ++i) {
			f.values[i] = f.argumentos.get(i).eval(this.context);
		}
		return f.values;
	}

	@Override
	public Object visitaMetodo(Metodo metodo) throws EvalExceptions {
		Referencia referencia = (Referencia) this.context.lookup(metodo.idReferencia);
		
		return evalFunction(metodo, referencia);
	}

	@Override
	public Object visitaConstructor(Constructor constructor) throws EvalExceptions {
		Referencia referencia = new Referencia(constructor.clase);
		
		return evalFunction(constructor, referencia);
	}

	private Tipo evalFunction(Funcion funcion, Referencia referencia) throws EvalExceptions {
		Tipo __return__;
		
		this.execFunctionInNewContext(funcion, referencia);
		__return__ = returnValue(funcion, referencia);
		this.execFinalizeFunction(funcion, referencia);
		return __return__;		
	}
	
	private void execFunctionInNewContext(Funcion funcion, Referencia referencia) throws EvalExceptions {
		this.context = new Context(this.context);

		this.context.insertParameters(funcion);
		referencia.instancia.insertInContext(this.context);
		funcion.bloque.visita(this);
	}

	private void execFinalizeFunction(Funcion funcion, Referencia referencia) throws EvalExceptions {
		this.context.modifyInstance(referencia);
		this.context.upContext();
	}
	
	private Tipo returnValue(Funcion funcion, Referencia referencia) throws EvalExceptions {
		Tipo __return__ = this.context.lookup(Context.RETURN);			

		if (funcion instanceof Constructor) {
			__return__ = referencia;
		}
		return __return__;		
	}

	// Funcion de ejecucion de ordenes...
	public Object exec(Comando c) throws EvalExceptions {
		return c.visita(this);
	}

	public Tipo getValueToTest(String id) throws EvalExceptions {
		return this.context.lookup(id);
	}
	
	@Override
	public String toString() {
		return this.context.toString();
	}

}