package com.master.mv.opcional;

import java.util.ArrayList;
import java.util.List;

import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.opcional.clases.Punto;

public class Clases {	
	
	private List<Clase> clases;
	
	public Clases() {
		this.clases = new ArrayList<>();
	}
	
	public void generateClassPunto() throws EvalExceptions {
		this.clases.add(new Punto());
	}
	
	public Clase getClaseByName(String name) throws EvalExceptions {
		for (Clase c : this.clases) {
			if (c.id.equals(name)) {
				return c;
			}
		}
		throw new EvalExceptions("The class not exists!! " + name);
	}
}
