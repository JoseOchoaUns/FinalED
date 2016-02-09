package TDAGraph;

import TDALista.Position;


/**
 * 
 * @author Comision 2 : Jose Ochoa, Franco Raniolo
 *
 */


public class Arco<E> implements Edge<E> {
	/**
	 * Rotulo del arco
	 */
	private E rotulo;
	
	/**
	 * Vertice destino del arco
	 */
	private Vertice<E> suc;
	
	/**
	 * Vertice origen del arco
	 */
	private Vertice<E> pre;
	
	/**
	 * Posicion en la lista de arcos del vertice origen
	 */
	private Position<Arco<E>> posV1; 
	
	/**
	 * Posicion en la lista de arcos del vertice destino
	 */
	private Position<Arco<E>> posV2;
	
	/**
	 * Posicion en la lista de arcos del grafo
	 */
	private Position<Arco<E>> posLA;
	
	/**
	 * Constructor de la clase arco, inicializa el rotulo y los vertices origen y destino
	 * @param r Rotulo
	 * @param v1 Vertice origen
	 * @param v2 Vertice destino
	 */
	public Arco(E r, Vertice<E> v1, Vertice<E> v2){
		rotulo = r;
		pre = v1;
		suc = v2;
	}
	
	public E element(){
		return rotulo;
	}
	
	/**
	 * Devuelve el vertice origen del arco
	 * @return Vertice origen del arco
	 */
	public Vertice<E> getPredecesor(){
		return pre;
	}
	
	/**
	 * Devuelve el vertice destino del arco
	 * @return Vertice destino del arco
	 */
	public Vertice<E> getSucesor(){
		return suc;
	}
	/**
	 * Retorna la posicion en la lista de arcos del vertice origen
	 * @return posicion en la lista de arcos del vertice origen
	 */
	public Position<Arco<E>> getPosEnSaliente(){
		return posV1;
	}
	
	/**
	 * Retorna la posicion en la lista de arcos del grafo
	 * @return posicion en la lista de arcos del grafo
	 */
	public Position<Arco<E>> getPosEnArco(){
		return posLA;		
	}
	
	/**
	 * Retorna la posicion en la lista de arcos del vertice destino
	 * @return posicion en la lista de arcos del vertice destino
	 */
	public Position<Arco<E>> getPosEnEntrante(){
		return posV2;
	} 
	
	/**
	 * Setea la posicion en la lista de arcos del vertice origen
	 * @param pos posicion en la lista de arcos del vertice origen
	 */
	public void setPosEnSaliente(Position<Arco<E>> pos){
		posV1 = pos;
	}
	
	/**
	 * Setea la posicion en la lista de arcos del vertice destino
	 * @param pos posicion en la lista de arcos del vertice destino
	 */
	public void setPosEnEntrante(Position<Arco<E>> pos){
		posV2 = pos;
	}
	
	/**
	 * Setea la posicion en la lista de arcos del grafo
	 * @param pos posicion en la lista de arcos del grafo
	 */
	public void setPosEnArcos(Position<Arco<E>> pos){
		posLA = pos;
	}	
	
	/**
	 * Setea el valor del rotulo
	 * @param e Valor del rotulo
	 */
	public void setElement(E e){
		rotulo = e;
	}
	
	
	
	

	
	
}