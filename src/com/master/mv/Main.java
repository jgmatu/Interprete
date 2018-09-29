package com.master.mv;

import java.util.Iterator;

import com.master.mv.comandos.Comando;
import com.master.mv.decompiler.Decompilador;
import com.master.mv.exceptions.EvalExceptions;
import com.master.mv.exceptions.OperationException;

public class Main {
	
	public static void main(String[] args) throws EvalExceptions {						
		Instruciones instrucciones = new Instruciones();
		
		try {
			instrucciones.generateIntrucciones();		
			runInstrucciones(instrucciones);
		} catch (OperationException e) {
			System.err.println("Operation exception : " + e.toString());
		} catch (EvalExceptions e) {
			System.err.println("Evaluate exception : " + e.toString());
		} catch (ClassCastException e) {
			System.err.println("Runtime error : Incompatible types class cast exception!" + e.toString());			
		}
		
		Decompilador decompiler = new Decompilador(instrucciones);
		decompiler.decompiler("");
	}
	
	private static void runInstrucciones(Instruciones instrucciones) throws EvalExceptions {
		Iterator<Comando> run = instrucciones.iterateInstrucciones();			
		Interprete interprete = new Interprete();
		
		while (run.hasNext()) {
			Comando instruccion = run.next();
			interprete.exec(instruccion);
		}
	}
}
