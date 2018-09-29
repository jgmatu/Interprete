package com.master.mv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.master.mv.comandos.Assignacion;
import com.master.mv.comandos.Comando;
import com.master.mv.comandos.Declaracion;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.expresiones.Constante;
import com.master.mv.expresiones.Constructor;
import com.master.mv.expresiones.Expresion;
import com.master.mv.expresiones.Metodo;
import com.master.mv.expresiones.Variable;
import com.master.mv.opcional.Clase;
import com.master.mv.opcional.Clases;
import com.master.mv.opcional.clases.Punto;
import com.master.mv.types.Decimal;
import com.master.mv.types.Entero;
import com.master.mv.types.Referencia;
import com.master.mv.utils.Context;

public class Instruciones {

	private Clase punto;
	private List<Comando> instrucciones;
	
	public Instruciones() {
		this.instrucciones = new ArrayList<>();
	}
	
	public void generateIntrucciones() throws EvalExceptions {
		
		this.punto = getClasePunto();
		this.instrucciones = new ArrayList<>();

		this.instrucciones.add(new Declaracion(new Referencia(punto), "puntoA"));
		this.instrucciones.add(new Declaracion(new Referencia(punto), "puntoB"));
		this.instrucciones.add(new Declaracion(new Entero(), "x"));
		this.instrucciones.add(new Declaracion(new Entero(), "y"));

		constructor("puntoA");
		constructor("puntoB");

		this.instrucciones.add(new Assignacion("x", new Constante(new Entero(7))));
		this.instrucciones.add(new Assignacion("y", new Constante(new Entero(13))));

		instruccionSetX("puntoB", new Variable("x"));
		instruccionSetY("puntoB", new Variable("y"));

		instruccionGetX("puntoA", "x");
		instruccionGetY("puntoB", "y");
		instruccionDistance("puntoA", "puntoB");
	}
	
	private void constructor(String idRef) {
		Constructor constructor = punto.constructor;
		constructor.setModeCall(new ArrayList<>());

		this.instrucciones.add(new Assignacion(idRef, constructor));
	}

	private void instruccionDistance(String idRefA, String idRefB) throws EvalExceptions {
		ArrayList<Expresion> arguments = new ArrayList<>();
		arguments.add(new Variable(idRefB));

		this.instrucciones.add(new Declaracion(new Decimal(), "distance"));
		this.instrucciones.add(new Assignacion("distance", getMetodo(idRefA, "distance", arguments)));
	}

	private void instruccionGetX(String idRef, String idVar) throws EvalExceptions {
		ArrayList<Expresion> arguments = new ArrayList<>();

		this.instrucciones.add(new Assignacion(idVar, getMetodo(idRef, "getX", arguments)));
	}

	private void instruccionGetY(String idRef, String idVar) throws EvalExceptions {
		ArrayList<Expresion> arguments = new ArrayList<>();

		this.instrucciones.add(new Assignacion(idVar, getMetodo(idRef, "getY", arguments)));
	}

	private void instruccionSetX(String idRef, Expresion expr) throws EvalExceptions {
		ArrayList<Expresion> arguments = new ArrayList<>();
		arguments.add(expr);

		this.instrucciones.add(new Assignacion(Context.VOID, getMetodo(idRef, "setX", arguments)));
	}

	private void instruccionSetY(String idRef, Expresion expr) throws EvalExceptions {
		ArrayList<Expresion> arguments = new ArrayList<>();
		arguments.add(expr);

		this.instrucciones.add(new Assignacion(Context.VOID, getMetodo(idRef, "setY", arguments)));
	}

	private Metodo getMetodo(String idRef, String id, List<Expresion> arguments) throws EvalExceptions {
		Metodo metodo = this.punto.getMetodoToExecById(id);

		metodo.setModeCall(idRef, arguments);
		return metodo;
	}

	private Clase getClasePunto() throws EvalExceptions {
		Clases clases = new Clases();
		clases.generateClassPunto();

		Clase punto = clases.getClaseByName(Punto.PUNTO);
		return punto;
	}

	public Iterator<Comando> iterateInstrucciones() {
		return this.instrucciones.iterator();
	}
}
