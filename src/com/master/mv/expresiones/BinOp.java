package com.master.mv.expresiones;


import com.master.mv.Visitor;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.types.Entero;
import com.master.mv.types.Tipo;
import com.master.mv.utils.Context;
import com.master.mv.utils.Operador;

public class BinOp extends Expresion {
	
	public Operador op;
	public Expresion e1, e2;

	public BinOp(Expresion e1, Expresion e2, Operador op) {
		this.e1 = e1;
		this.e2 = e2;
		this.op = op;
	}
	
	@Override
	public Tipo eval(Context ctx) throws EvalExceptions {
		Entero v1 = (Entero) e1.eval(ctx);
		Entero v2 = (Entero) e2.eval(ctx);
		return op.apply(v1, v2);
	}

	@Override
	public Object visita(Visitor v) throws EvalExceptions {
		return v.visitaBinOp(this);
	}
	
	@Override
	public String toString() {
		return this.e1.toString() + " " + this.op.toString() + " " + this.e2.toString();
	}
	
	@Override
	public String decompiler() {
		return this.toString();
	}
	
}
