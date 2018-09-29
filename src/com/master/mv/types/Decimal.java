package com.master.mv.types;

public class Decimal extends Tipo {

	public Float v;

	public Decimal() {
		this.v = Float.MIN_VALUE;
	}
	
	public Decimal(float v) {
		this.v = v;
	}
	
	@Override
	public String toString() {
		return String.format("%f", this.v);
	}
 	
}
