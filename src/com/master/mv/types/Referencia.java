package com.master.mv.types;

import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.opcional.Clase;
import com.master.mv.opcional.Instancia;

public class Referencia extends Tipo {
	
	public String id;
	public Clase clase;
	public Instancia instancia;
	
	public Referencia(Clase clase) throws EvalExceptions {
		this.instancia = new Instancia(clase.atributos);
		this.clase = clase;
	}

	@Override 
	public String toString() {
		return "Clase " + this.clase.id + " " + this.instancia.toString();
	}
}
