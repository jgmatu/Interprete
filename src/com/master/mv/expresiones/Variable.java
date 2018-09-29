package com.master.mv.expresiones;


import com.master.mv.Visitor;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.types.Tipo;
import com.master.mv.utils.Context;

public class Variable extends Expresion {
	
	public String id;
	
	public Variable(String id) {
		this.id = id;
	}
	
	@Override
	public Tipo eval(Context ctx) throws EvalExceptions {
		return ctx.lookup(id);
	}

	@Override
	public Object visita(Visitor v) throws EvalExceptions {
		return v.visitaVar(this);
	}
	
	@Override
	public String toString() {
		return this.id;
	}
	
	@Override
	public String decompiler() {
		return this.toString();
	}
}
