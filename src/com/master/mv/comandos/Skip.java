package com.master.mv.comandos;


import com.master.mv.Visitor;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.utils.Context;

public class Skip extends Comando {

	@Override
	public void exec(Context ctx) {
		;
	}

	public Object visita(Visitor v) throws EvalExceptions {
		return v.visitaSkip(this);
	}
	
	@Override
	public String toString() {
		return ";\n";
	}
	
	@Override
	public String decompiler() {
		return this.toString();
	}
}
