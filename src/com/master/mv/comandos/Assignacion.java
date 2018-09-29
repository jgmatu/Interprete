package com.master.mv.comandos;

import com.master.mv.Visitor;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.expresiones.Expresion;
import com.master.mv.types.Tipo;
import com.master.mv.utils.Context;

public class Assignacion extends Comando {

	public String id;
	public Expresion e;

	public Assignacion(String id, Expresion e) {
		this.id = id;
		this.e = e;
	}
	
	public Object visita(Visitor v) throws EvalExceptions {
		return v.visitaAssign(this);
	}

	@Override
	public void exec(Context ctx) throws EvalExceptions {
		Tipo v = e.eval(ctx);
		ctx.update(id, v);
	}

	@Override 
	public String toString() { 
		if (this.id.equals(Context.VOID)) {
			return this.e.toString() + ";";
		}
		return this.id + " = "  + this.e.toString() + ";";
	}
	
	@Override
	public String decompiler() {
		return this.toString();
	}
}
