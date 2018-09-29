package com.master.mv.opcional.clases;

import java.util.ArrayList;
import java.util.List;

import com.master.mv.comandos.Assignacion;
import com.master.mv.comandos.Comando;
import com.master.mv.comandos.Declaracion;
import com.master.mv.comandos.Return;
import com.master.mv.comandos.Sequence;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.expresiones.BinOp;
import com.master.mv.expresiones.Constante;
import com.master.mv.expresiones.Constructor;
import com.master.mv.expresiones.Expresion;
import com.master.mv.expresiones.Metodo;
import com.master.mv.expresiones.Variable;
import com.master.mv.opcional.Atributo;
import com.master.mv.opcional.Clase;
import com.master.mv.types.Entero;
import com.master.mv.utils.Operador;
import com.master.mv.utils.Operador.Operator;

public class Punto extends Clase {

	public static final String PUNTO = "Punto";

	public Punto() throws EvalExceptions {
		this.id = PUNTO;

		this.atributos = new ArrayList<>();
		this.atributos.add(new Atributo("x", new Entero()));
		this.atributos.add(new Atributo("y", new Entero()));

		this.metodos = new ArrayList<>();
		this.metodos.add(distance());
		this.metodos.add(setX());
		this.metodos.add(setY());
		this.metodos.add(getX());
		this.metodos.add(getY());

		Comando bloque =  new Assignacion("this.x", new Constante(new Entero(1)));
		bloque = new Sequence(bloque, new Assignacion("this.y", new Constante(new Entero(1))));
		
		this.constructor = new Constructor(this, new ArrayList<>(), bloque);
	}

	private Metodo getX() {
		Metodo getX = new Metodo();

		getX.id = "getX";
		getX.parametros = new ArrayList<>();
		getX.bloque = new Return(new Variable("this.x"));
		return getX;
	}

	private Metodo getY() {
		Metodo getY = new Metodo();

		getY.id = "getY";
		getY.parametros = new ArrayList<>();
		getY.bloque = new Return(new Variable("this.y"));
		return getY;

	}

	private Metodo setX() {
		List<String> parametros = new ArrayList<>();
		parametros.add("x");

		Metodo setX = new Metodo();
		setX.id = "setX";
		setX.parametros = parametros;
		setX.bloque = new Assignacion("this.x", new Variable("x"));
		return setX;

	}

	private Metodo setY() {
		List<String> parametros = new ArrayList<>();
		parametros.add("y");

		Metodo setY = new Metodo();
		setY.id = "setY";
		setY.parametros = parametros;
		setY.bloque = new Assignacion("this.y", new Variable("y"));
		return setY;
	}

	private Metodo distance() {
		List<String> parametros = new ArrayList<>();
		parametros.add("p");

		Comando declareX = new Declaracion(new Entero(), "x");
		Comando declareY = new Declaracion(new Entero(), "y");

		Metodo getX = getX();
		getX.setModeCall("p", new ArrayList<>());
		declareX = new Sequence(declareX, new Assignacion("x", getX));

		Metodo getY = getY();
		getY.setModeCall("p", new ArrayList<>());
		declareY = new Sequence(declareY, new Assignacion("y", getY));

		Comando initialize = new Sequence(declareX, declareY);
		initialize = new Sequence(initialize, new Declaracion(new Entero(), "distance"));
		initialize = new Sequence(initialize, new Assignacion("distance", new Constante(new Entero(0))));

		Expresion reduceX = new BinOp(new Variable("x"), new Variable("this.x"), new Operador(Operator.LESS));
		Expresion reduceY = new BinOp(new Variable("y"), new Variable("this.y"), new Operador(Operator.LESS));

		Comando powX = new Assignacion("x", new BinOp(reduceX, reduceX, new Operador(Operator.MULT)));
		Comando powY = new Assignacion("y", new BinOp(reduceY, reduceY, new Operador(Operator.MULT)));

		Comando pow = new Sequence(powX, powY);
		pow = new Sequence(pow, new Assignacion("distance", new BinOp(new Variable("x"), new Variable("y"), new Operador(Operator.SUM))));

		Comando sqrt = new Assignacion("distance", new BinOp(new Variable("distance"), new Variable("distance"), new Operador(Operator.SQRT)));
		sqrt = new Sequence(pow, sqrt);

		Comando bloque = new Sequence(initialize, sqrt);
		bloque = new Sequence(bloque, new Return(new Variable("distance")));

		Metodo distance = new Metodo();
		distance.id = "distance";
		distance.parametros = parametros;
		distance.bloque = bloque;
		return distance;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
