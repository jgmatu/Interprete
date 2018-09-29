package com.master.mv.expresiones;

import com.master.mv.Visitor;
import com.master.mv.types.Entero;
import com.master.mv.utils.Context;

public class Constante extends Expresion {
	
	public Entero n;
	
	public Constante(Entero n) {
		this.n = n;
	}
	
	@Override
	public Entero eval(Context ctx) {
		return n;
	}

	@Override
	public Object visita(Visitor v) {
		return v.visitaConst(this);
	}
	
	@Override
	public String toString() {
		return this.n.toString();
	}
	
	@Override
	public String decompiler() {
		return this.n.v.toString();
	}
}