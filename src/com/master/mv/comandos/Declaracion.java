package com.master.mv.comandos;

import com.master.mv.Visitor;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.types.Decimal;
import com.master.mv.types.Entero;
import com.master.mv.types.Referencia;
import com.master.mv.types.Tipo;
import com.master.mv.utils.Context;

public class Declaracion extends Comando {

	public String id;
	public Tipo tipo;
	
	public Declaracion(Tipo tipo, String id) {
		this.id = id;
		this.tipo = tipo;
	}
	
	@Override
	public Object visita(Visitor v) throws EvalExceptions {
		return v.visitaDeclare(this);
	}

	@Override
	public void exec(Context ctx) throws EvalExceptions {
		ctx.insert(this.id, this.tipo);
	}

	@Override
	public String toString() {
		if (this.tipo instanceof Referencia) {
			Referencia referencia = (Referencia) this.tipo;
			return referencia.clase.id + " " + this.id + ";";
		}
		if (this.tipo instanceof Entero) {
			return "int " + this.id + ";";
		}
		if (this.tipo instanceof Decimal) {
			return "float " + this.id + ";";
		}
		return "Unknow declare type";
	}
	
	@Override
	public String decompiler() {
		return this.toString();
	}

}
