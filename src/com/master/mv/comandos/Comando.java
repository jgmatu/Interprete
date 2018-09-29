package com.master.mv.comandos;

import com.master.mv.Visitor;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.utils.Context;

public abstract class Comando {

	public abstract void exec(Context ctx) throws EvalExceptions;

	public abstract Object visita(Visitor v) throws EvalExceptions;

	public abstract String decompiler();
}
