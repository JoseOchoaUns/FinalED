package TDAABBB2;

import TDAMapeo.Entrada;

public class NodoABB<K extends Comparable<K>,V> {
	private Entrada<K,V> entrada;
	private NodoABB<K,V> padre,izq,der;
	public NodoABB(Entrada<K,V> r, NodoABB<K,V> p){
		entrada = r;
		padre = p;
		izq = null;
		der = null;
	}
	
	public Entrada<K, V> getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada<K, V> entrada) {
		this.entrada = entrada;
	}

	public NodoABB<K,V> getPadre() { return padre; }
	public NodoABB<K,V> getIzq() { return izq; }
	public NodoABB<K,V> getDer() { return der; }
	
		
	
	public void setIzq( NodoABB<K,V> izq ) { this.izq = izq; }
	public void setDer( NodoABB<K,V> der ) { this.der = der; }
	public void setPadre( NodoABB<K,V> padre ) { this.padre = padre; }
}
