package com.master.mv.expresiones;

import com.master.mv.Visitor;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.types.Tipo;
import com.master.mv.utils.Context;

public abstract class Expresion {

	public abstract Tipo eval(Context ctx) throws EvalExceptions;

	public abstract Object visita(Visitor v) throws EvalExceptions;

	public abstract String decompiler();

}
