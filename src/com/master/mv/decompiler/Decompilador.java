package com.master.mv.decompiler;


import java.util.Iterator;
import com.master.mv.Instruciones;
import com.master.mv.comandos.Comando;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.opcional.Clase;
import com.master.mv.opcional.Clases;
import com.master.mv.opcional.clases.Punto;

/**
 * El decompilador de java dadas las instrucciones del int�rprete devuelve el c�digo fuente
 * de un programa en Java.
 * 
 * @author Javier Guti�rrez-Maturana S�nchez
 *
 */
public class Decompilador {
	
	private Instruciones instrucciones;
	private Clases clases;
	
	public Decompilador(Instruciones instrucciones) throws EvalExceptions {
		this.instrucciones = instrucciones;
		this.clases = new Clases();
		this.clases.generateClassPunto();
	}

	
	public void decompiler(String filename) throws EvalExceptions {
		Clase punto = this.clases.getClaseByName(Punto.PUNTO) ;
		Iterator<Comando> iterator = this.instrucciones.iterateInstrucciones();
		
		System.out.println("public class Main {\n" +  punto.toString() +
				"\tpublic static void main(String[] args) throws EvalExceptions {");
		while (iterator.hasNext()) {
			Comando instruccion = iterator.next();
			System.out.println("\t\t" + instruccion.toString());
		}
		System.out.println("\t}");
		System.out.println("}");
	}
	
}
