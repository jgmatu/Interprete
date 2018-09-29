package com.master.mv;

import com.master.mv.comandos.Assignacion;
import com.master.mv.comandos.Declaracion;
import com.master.mv.comandos.If;
import com.master.mv.comandos.Return;
import com.master.mv.comandos.Sequence;
import com.master.mv.comandos.Skip;
import com.master.mv.comandos.While;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.expresiones.BinOp;
import com.master.mv.expresiones.Constante;
import com.master.mv.expresiones.Constructor;
import com.master.mv.expresiones.Funcion;
import com.master.mv.expresiones.Metodo;
import com.master.mv.expresiones.Variable;

public abstract class Visitor {
	
	public abstract Object visitaWhile(While w) throws EvalExceptions;

	public abstract Object visitaIf(If i) throws EvalExceptions;

	public abstract Object visitaSeq(Sequence s) throws EvalExceptions;

	public abstract Object visitaAssign(Assignacion a) throws EvalExceptions;

	public abstract Object visitaSkip(Skip s)throws EvalExceptions;

	public abstract Object visitaBinOp(BinOp b) throws EvalExceptions;

	public abstract Object visitaVar(Variable v) throws EvalExceptions;

	public abstract Object visitaConst(Constante c);
	
	public abstract Object visitaDeclare(Declaracion id) throws EvalExceptions;
	
	public abstract Object visitaReturn(Return r)  throws EvalExceptions;

	public abstract Object visitaFuncion(Funcion funcion) throws EvalExceptions;	
	public abstract Object visitaMetodo(Metodo metodo)  throws EvalExceptions;
	public abstract Object visitaConstructor(Constructor constructor) throws EvalExceptions;
}
