package com.master.mv.opcional;

import com.master.mv.types.Tipo;

public class Atributo {

	private String id;
	private Tipo value;
	
	public Atributo(String id, Tipo value) {
		this.id = id;
		this.value = value;
	}
	
	public String getId() {
		return this.id;
	}
	
	public Tipo getValue() {
		return this.value;
	}
}
