package com.master.mv.utils;

import com.master.mv.exceptions.OperationException;
import com.master.mv.types.Booleano;
import com.master.mv.types.Decimal;
import com.master.mv.types.Entero;
import com.master.mv.types.Tipo;

public class Operador {

	public static enum Operator {
		SUM, LESS, MULT, DIV, SQRT, GT, LT, EQ, GE, LE, NEQ, NOT
	};

	private static final String INFO_INCOMPATIBLE_TYPES = "Relational operation incompatible type between binary operators";

	private Operator op;

	public Operador(Operator op) {
		this.op = op;
	}

	public Tipo apply(Tipo t1, Tipo t2) throws OperationException {
		switch (this.op) {

		case SUM:
			return new Entero(getCastEntero(t1).v + getCastEntero(t2).v);
		case LESS:
			return new Entero(getCastEntero(t1).v - getCastEntero(t2).v);
		case MULT:
			return new Entero(getCastEntero(t1).v * getCastEntero(t2).v);
		case DIV:
			if (getCastEntero(t2).v == 0) {
				throw new OperationException("Divided by 0");
			}
			return new Entero(getCastEntero(t1).v / getCastEntero(t2).v);
		case SQRT:
			if (getCastEntero(t1).v < 0) {
				throw new OperationException("SQRT not negative value expected!");
			}
			return new Decimal((float) Math.sqrt(getCastEntero(t1).v));

		case GT:
			return new Booleano(getCastEntero(t1).v > getCastEntero(t2).v);
		case LT:
			return new Booleano(getCastEntero(t1).v < getCastEntero(t2).v);
		case EQ:
			return new Booleano(getCastEntero(t1).v == getCastEntero(t2).v);
		case GE:
			return new Booleano(getCastEntero(t1).v >= getCastEntero(t2).v);
		case LE:
			return new Booleano(getCastEntero(t1).v <= getCastEntero(t2).v);
		case NEQ:
			return new Booleano(getCastEntero(t1).v != getCastEntero(t2).v);
		case NOT:
			return new Booleano(!getCastBooleano(t1).b);

		default:
			throw new OperationException("Not valid operation");
		}
	}

	private Booleano getCastBooleano(Tipo t) throws OperationException {
		if (!(t instanceof Booleano)) {
			throw new OperationException(INFO_INCOMPATIBLE_TYPES);
		}
		Booleano b = (Booleano) t;
		return b;
	}

	private Entero getCastEntero(Tipo t) throws OperationException {
		if (!(t instanceof Entero)) {
			throw new OperationException(INFO_INCOMPATIBLE_TYPES);
		}
		Entero v = (Entero) t;
		return v;
	}

	@Override
	public String toString() {
		switch (this.op) {
		case SUM:
			return "+";
		case LESS:
			return "-";
		case MULT:
			return "*";
		case DIV:
			return "/";
		case GT:
			return ">";
		case LT:
			return "<";
		case EQ:
			return "==";
		case GE:
			return ">=";
		case LE:
			return "<=";
		case NEQ:
			return "!=";
		case NOT:
			return "!";
		case SQRT:
			return "sqrt";
		default:
			return "invalid!";
		}
	}

}
