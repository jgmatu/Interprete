package com.master.mv.comandos;

import com.master.mv.Visitor;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.expresiones.Expresion;
import com.master.mv.types.Booleano;
import com.master.mv.utils.Context;

public class If extends Comando {

	public Expresion e;
	public Comando c1, c2;

	public If(Expresion e, Comando c1, Comando c2) {
		this.e = e;
		this.c1 = c1;
		this.c2 = c2;
	}
	
	@Override
	public void exec(Context ctx) throws EvalExceptions {
		try {
			Booleano v = (Booleano) e.eval(ctx);
			if (v.b) {
				c1.exec(ctx);
			} else {
				c2.exec(ctx);			
			}			
		} catch (ClassCastException e) {
			throw new EvalExceptions("Excepted Boolean tipo on conditional expr if");
		}
	}

	@Override
	public Object visita(Visitor v) throws EvalExceptions {
		return v.visitaIf(this);
	}
	
	@Override
	public String toString() {
		return "if (" + e.toString() + ") {\n" +
					"\t" + c1.toString() +
				"}\n" +
				"else {\n" +
					"\t" + c2.toString() +
				"}\n";
	}
	
	@Override
	public String decompiler() {
		return this.toString();
	}
}
