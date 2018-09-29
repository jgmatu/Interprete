package com.master.mv.comandos;

import com.master.mv.Visitor;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.expresiones.Expresion;
import com.master.mv.types.Booleano;
import com.master.mv.utils.Context;

public class While extends Comando {

	public Expresion e;
	public Comando c;

	public While(Expresion e, Comando c) {
		this.e = e;
		this.c = c;
	}
	
	@Override
	public void exec(Context ctx) throws EvalExceptions {
		try {
			for (;;) {
				Booleano v = (Booleano) e.eval(ctx);
				if (!v.b) {
					break;				
				}
				c.exec(ctx);
			}			
		} catch (ClassCastException e) {
			throw new EvalExceptions("While boolean value excepted!!");
		}
	}
	
	public Object visita(Visitor v) throws EvalExceptions {
		return v.visitaWhile(this);
	}

	@Override 
	public String toString() {
		return "while ( " + e.toString() + " ) { \n" +
					"\t" + c.toString() +
				"}\n";
	}
	
	@Override
	public String decompiler() {
		return this.toString();
	}
}
