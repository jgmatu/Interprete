package com.master.mv.utils;

import java.util.HashMap;
import java.util.Map;

import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.expresiones.Funcion;
import com.master.mv.types.Booleano;
import com.master.mv.types.Decimal;
import com.master.mv.types.Entero;
import com.master.mv.types.Referencia;
import com.master.mv.types.Tipo;

public class Context {
	
	public static final String VOID = "__void__";
	public static final String RETURN = "__return__";
	
	private Map<String, Tipo> ctx;
	private Context father;
	
	public Context() {
		this.ctx = new HashMap<>();
		this.ctx.put(RETURN, new Entero(0));
		this.ctx.put(VOID, new Entero(0));
		this.father = null;
	}
	
	public Context(Context father) {
		this();
		this.father = father;
	}
	
	public void upContext() throws EvalExceptions {
		if (father == null) {
			throw new EvalExceptions("There is not context to up you are in root context!");
		}
		this.ctx = father.ctx;
		father = father.father;
	}
	
	public Tipo lookup(String id) throws EvalExceptions {
		if (this.ctx.containsKey(id)) {
			return this.ctx.get(id);
		}
		if (this.father == null) {
			throw new EvalExceptions("The value query not exists!! " + id);
		}
		return this.father.lookup(id);
	}
	
	public void insert(String id, Tipo v) throws EvalExceptions {
		if (this.ctx.containsKey(id)) {
			throw new EvalExceptions("The id already exists " + id);
		}
		this.ctx.put(id, v);
	}
	
	public void update(String id, Tipo val) throws EvalExceptions {
		if (!this.ctx.containsKey(id)) {
			throw new EvalExceptions("The id not exists!!! " + id);
		}
		this.ctx.put(id, val);
	}

	public void modifyInstance(Referencia r) {
		for (Map.Entry<String, Tipo> entry : this.ctx.entrySet()) {
			String id = entry.getKey();
			if (r.instancia.estado.containsKey(id)) {
				r.instancia.estado.put(id, entry.getValue());
			}
		}		
	}
	
	public void insertParameters(Funcion funcion) throws EvalExceptions {
		for (int i = 0; i < funcion.values.length; ++i) {
			this.insert(funcion.parametros.get(i), funcion.values[i]);
		}
	}
	
	@Override
	public String toString() {
		return formatContext(new StringBuffer());
	}
	
	private String formatContext(StringBuffer format) {
		format.append("\n\n **** START CONTEXT **** \n\n");
		for (Map.Entry<String, Tipo> entry : this.ctx.entrySet()) {
			format.append(entry.getKey() + " : " + formatTipo(entry.getValue()) + '\n');
		}
		if (father == null) {
			format.append("\n\n **** END CONTEXT **** \n\n");
			return format.toString();
		}
		format.append("\n\n **** END CONTEXT **** \n\n");
		return this.father.formatContext(format);		
	}
	
	private String formatTipo(Tipo t) {
		if (t instanceof Entero) {
			Entero e = (Entero) t;
			return String.format("Entero: %d", e.v);
		}
		if (t instanceof Decimal) {
			Decimal d = (Decimal) t;
			return String.format("Decimal : %f", d.v);
		}
		if (t instanceof Referencia) {
			Referencia r = (Referencia) t;
			return String.format("Referencia a %s", r.clase.id) + " " + r.instancia.toString();
		}
		if (t instanceof Booleano) {
			Booleano b = (Booleano) t;
			return String.format("Booleano : %b", b.b);
		}
		return "Unknown Type";
	}
}
