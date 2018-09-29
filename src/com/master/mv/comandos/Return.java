package com.master.mv.comandos;

import com.master.mv.Visitor;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.expresiones.Expresion;
import com.master.mv.utils.Context;

public class Return extends Comando {

	public Expresion expr;
	
	public Return(Expresion expr) {
		this.expr = expr;
	}
	 
	@Override
	public void exec(Context ctx) throws EvalExceptions {
		ctx.update(Context.RETURN, expr.eval(ctx));
	}

	@Override
	public Object visita(Visitor v) throws EvalExceptions {
		return v.visitaReturn(this);
	}

	@Override
	public String toString() {
		return "return " + expr.toString() + ";" + '\n';
	}
	
	@Override
	public String decompiler() {
		return this.toString();
	}
}
