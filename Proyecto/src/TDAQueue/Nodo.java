package TDAQueue;

/**
 * 
 * @author COMISION 2 : Ochoa Jose y Franco Raniolo
 *
 */

public class Nodo<E> {
	/**
	 * Rotulo del nodo
	 */
	private E elemento;
	
	/**
	 * Nodo siguiente
	 */
	private Nodo<E> siguiente;
	
	/**
	 * Constructor del nodo
	 * @param item Rotulo 
	 * @param sig Proximo nodo
	 */
	
	public Nodo( E item, Nodo<E> sig ){
		elemento=item;
		siguiente=sig; 
	}
	
	public Nodo() { 
		this(null, null);
	}
	/**
	 * 
	 * @return El rotulo del nodo
	 */
	public E getElemento(){
		return elemento;
	}
	
	/**
	 * Setea el rotulo con el elemento pasado por parametros
	 * @param elemento Nuevo rotulo
	 */
	public void setElemento( E elemento ){ 
		this.elemento=elemento;
	}
	
	/**
	 * 
	 * @return Retorna el siguiente nodo
	 */
	public Nodo<E> getNext(){
		return siguiente;
	}
	
	/**
	 * Setea el siguiente nodo
	 * @param siguiente Siguiente nodo
	 */	
	public void setNext( Nodo<E> siguiente ) {
		this.siguiente = siguiente; 
	}
}