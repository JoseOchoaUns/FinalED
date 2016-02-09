package PriorityQueue;

import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;


public class Alumno implements java.util.Comparator<Alumno>
{
	private String legajo;
	private String nombre;
	private PositionList <ExamenFinal> finales;
	
	public Alumno(String l, String n){
		legajo = l;
		nombre = n;
		finales = new ListaDoblementeEnlazada<ExamenFinal>();
	}
	
	public String getLegajo(){
		return legajo;
	}
	public String getNombre(){
		return nombre;
	}
	public void setLegajo(String s){
		legajo = s;
	}
	public void setNombre(String s){
		nombre = s;
	}
	public void agregaFinal(ExamenFinal e){
		finales.addLast(e);
	}
	public Iterable<ExamenFinal> iterador(){
		PositionList<ExamenFinal> lista = new ListaDoblementeEnlazada<ExamenFinal>();
		for(ExamenFinal e : finales)
			lista.addLast(e);
		return lista;
	}

	
	public int compare(Alumno a1, Alumno a2) {
		int promedioA1 = 0;
		int promedioA2 = 0;
		int contador = 0;
		for(ExamenFinal e : a1.iterador()){
			promedioA1 += e.getNota();
			contador ++;
		}
		if (contador != 0)
			promedioA1 = promedioA1 / contador;
		contador = 0;
		for(ExamenFinal e : a2.iterador()){
			promedioA2 += e.getNota();
			contador++;
		}
		if (contador != 0)
			promedioA2 /= contador;
		if(promedioA1 < promedioA2)
			return -1;
		else
			if(promedioA1 > promedioA2)
				return 1;
			else
				return 0;
	}
	
}
