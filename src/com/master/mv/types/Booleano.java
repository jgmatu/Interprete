package com.master.mv.types;

public class Booleano extends Tipo {
	
	public Boolean b;

	public Booleano(boolean b) {
		this.b = b;
	}
	
	@Override
	public String toString() {
		return String.format("%b", this.b);
	}
}
