package com.master.mv.opcional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.types.Tipo;
import com.master.mv.utils.Context;

public class Instancia {
	
	public static final String THIS = "this.";
	public Map<String, Tipo> estado;

	public Instancia() {
		this.estado = new HashMap<>();
	}
	
	public Instancia(List<Atributo> atributos) {
		this.estado = new HashMap<>();
		
		for (Atributo atributo : atributos) {
			this.estado.put(THIS + atributo.getId(), atributo.getValue());
		}
	}
	
	public void insertAttr(Atributo attr) throws EvalExceptions {
		String thisAttr = THIS + attr.getId();
		
		if (this.estado.containsKey(thisAttr)) {
			throw new EvalExceptions("The attr already exists! " + attr.getId());
		}
		this.estado.put(thisAttr, attr.getValue());
	}
	
	public Tipo searchAttr(String id) throws EvalExceptions {
		String thisId = THIS + id;
		
		if (!this.estado.containsKey(thisId)) {
			throw new EvalExceptions("The attr not exists!! " + id);
		}
		return this.estado.get(thisId);
	}
 	
	public void updateAttr(Atributo attr) throws EvalExceptions {
		String thisAttr = THIS + attr;
		
		if (!this.estado.containsKey(thisAttr)) {
			throw new EvalExceptions("The attr not exists!! " + attr);
		}
		this.estado.put(thisAttr, attr.getValue());
	}
	
	public void insertInContext(Context ctx) throws EvalExceptions {
		for (Map.Entry<String, Tipo> entry : this.estado.entrySet()) {
			ctx.insert(entry.getKey(), entry.getValue());
		}
	}
	
	@Override
	public String toString() {
		StringBuffer format = new StringBuffer();
		format.append('\n');
		for (Map.Entry<String, Tipo> entry : this.estado.entrySet()) {
			format.append(String.format("\t%s : %s\n", entry.getKey(), entry.getValue().toString()));
		}
		return format.toString();
	}
	
}
