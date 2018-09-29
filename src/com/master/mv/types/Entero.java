package com.master.mv.types;

public class Entero extends Tipo {

	public Integer v;

	public Entero() {
		this.v = Integer.MIN_VALUE;
	}
	
	public Entero(int v) {
		this.v = v;
	}
	
	@Override
	public String toString() {
		return String.format("%d", this.v);
	}
 	
}
