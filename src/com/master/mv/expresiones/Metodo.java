package com.master.mv.expresiones;

import java.util.List;

import com.master.mv.Visitor;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.types.Tipo;
import com.master.mv.utils.Context;

public class Metodo extends Funcion {
	
	public String idReferencia;
	
	public Metodo(Metodo m) {
		this.id = m.id;
		this.parametros = m.parametros;
		this.bloque = m.bloque;
	}
	
	public Metodo() {
		super();
		this.idReferencia = "";
	}

	public void setModeCall(String idRef, List<Expresion> arguments) {
		super.setModeCall(arguments);
		this.idReferencia = idRef;
	}
	
	@Override
	public Tipo eval(Context ctx) throws EvalExceptions {
		return super.eval(ctx);
	}

	@Override
	public Object visita(Visitor v) throws EvalExceptions {
		return v.visitaMetodo((Metodo) v.visitaFuncion(this));
	}
	
	@Override 
	public String toString() {
		return this.idReferencia + "." + this.id + "(" + decompileListExpresions(this.argumentos) + ")";		
	}
	
	@Override
	public String decompiler() {
		return "private " + this.id + "(" + decompileParameters() + ") {\n\t" + bloque.toString() + "\n}\n"; 
	}

	
	@Override
	protected String decompileListExpresions(List<Expresion> expresions) {
		return super.decompileListExpresions(expresions);
	}
}
