package com.master.mv.expresiones;

import java.util.List;

import com.master.mv.Visitor;
import com.master.mv.comandos.Comando;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.opcional.Clase;
import com.master.mv.types.Tipo;
import com.master.mv.utils.Context;

public class Constructor extends Funcion {
	
	public Clase clase;
	
	public Constructor(Constructor constructor) {
		super(constructor.clase.id, constructor.parametros, constructor.bloque);
		this.clase = constructor.clase;
	}
	
	public Constructor(Clase clase, List<String> parametros, Comando bloque) throws EvalExceptions {
		super(clase.id, parametros, bloque);
		this.clase = clase;
	}

	@Override
	public Tipo eval(Context ctx) throws EvalExceptions {
		return super.eval(ctx);
	}

	@Override
	public Object visita(Visitor v) throws EvalExceptions {
		return v.visitaConstructor((Constructor) v.visitaFuncion(this));
	}
	
	@Override
	public String toString() {
		return "new " + clase.id + "(" + decompileListExpresions(this.argumentos) + ")";
	}
	
	@Override
	public String decompiler() {
		return "\t" + this.id + "(" + decompileParameters() + ") {\n\t\t" + this.bloque.toString() + "\n\t}\n";
	}
}
