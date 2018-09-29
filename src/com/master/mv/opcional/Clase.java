package com.master.mv.opcional;

import java.util.List;

import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.expresiones.Constructor;
import com.master.mv.expresiones.Metodo;

public abstract class Clase {

	public String id;
	public List<Atributo> atributos;
	public List<Metodo> metodos;
	public Constructor constructor;

	public Metodo getMetodoToExecById(String id) throws EvalExceptions {
		for (Metodo m : this.metodos) {
			if (m.id.equals(id)) {
				return new Metodo(m);
			}
		}
		throw new EvalExceptions("The method not exists " + id);
	}

	@Override
	public String toString() {
		return "class " + this.id + " {\n" + atributesToString() + "\n" +
				this.constructor.decompiler() + metodosToString() + "\n}\n";
	}
	
	private String atributesToString() {
		StringBuffer format = new StringBuffer();

		for (Atributo atributo : this.atributos) {
			format.append("\tprivate " + atributo.getId() + ";\n");
		}
		return format.toString();
	}
	
	private String metodosToString() {
		StringBuffer format = new StringBuffer();
		
		for (Metodo m : this.metodos) {
			format.append(m.decompiler());
		}
		return format.toString();
	}
}
