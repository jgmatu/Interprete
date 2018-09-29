package com.master.mv.expresiones;


import java.util.List;

import com.master.mv.Visitor;
import com.master.mv.comandos.Comando;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.types.Tipo;
import com.master.mv.utils.Context;

public class Funcion extends Expresion {

	public String id;
	public List<String> parametros; // Lista de atributos para añadir el tipo en la definición del método. Se añade el valor despues nos cargamos el atributo values.
	public List<Expresion> argumentos;

	public Tipo[] values;
	public Comando bloque;

	public Funcion() {
		this.id = "?";
	}

	public Funcion(String id, List<String> parametros, Comando bloque) {
		this.id = id;
		this.parametros = parametros;
		this.bloque = bloque;
	}

	public void setModeCall(List<Expresion> arguments) {
		this.argumentos = arguments;
	}

	@Override
	public Tipo eval(Context ctx) throws EvalExceptions {
		return null;
	}

	@Override
	public Object visita(Visitor v) throws EvalExceptions {
		return v.visitaFuncion(this);
	}

	@Override
	public String toString() {
		return "public " + this.id + "( " + decompileListExpresions(this.argumentos) + " ) { \n" + bloque.toString() + "}\n";
	}

	@Override
	public String decompiler() {
		return this.toString();
	}

	protected String decompileListExpresions(List<Expresion> expresions) {
		StringBuffer format = new StringBuffer();

		for (int i = 0; i < expresions.size(); ++i) {
			format.append(expresions.get(i).decompiler());
			if (i != expresions.size() - 1) {
				format.append(", ");
			}
		}
		return format.toString();
	}
	
	protected String decompileParameters() {
		StringBuffer format = new StringBuffer();
		
		for (int i = 0; i < this.parametros.size(); ++i) {
			format.append(parametros.get(i));
			if (i != this.parametros.size() - 1) {
				format.append(", ");
			}
		}
		return format.toString();
	}
}
