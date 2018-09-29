package com.master.mv.comandos;


import com.master.mv.Visitor;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.utils.Context;

public class Sequence extends Comando {
	
	public Comando c1, c2;

	public Sequence(Comando c1, Comando c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	public Object visita(Visitor v) throws EvalExceptions {
		return v.visitaSeq(this);
	}

	@Override
	public void exec(Context ctx) throws EvalExceptions {
		this.c1.exec(ctx);
		this.c2.exec(ctx);
	}
	
	@Override
	public String toString() {
		return this.c1.toString()  +  this.c2.toString();
	}
	
	@Override
	public String decompiler() {
		return this.toString();
	}
}
