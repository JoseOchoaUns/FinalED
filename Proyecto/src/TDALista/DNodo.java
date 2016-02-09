package TDALista;
/**
 * 
 * @author Comision 2: Franco Raniolo y José Ochoa
 *
 * @param <E> Tipo del rotulo del nodo
 */
public class DNodo<E> implements Position<E> {
	
	/*
	 * Rotulo del nodo
	 */
	private E elemento; 
	/*
	 * Anterior es el nodo anterior en la lista doblemente enlazada a este nodo
	 * Siguiente es el nodo anterior en la lista doblemente enlazada a este nodo
	 */
	private DNodo<E> anterior,siguiente;
	
	/**
	 * Inicializa el nodo 
	 * @param elem Rotulo del nodo
	 * @param ant Nodo anterior
	 * @param sig Nodo siguiente
	 */
	public  DNodo(E elem, DNodo<E> ant, DNodo<E> sig) {
	elemento=elem;
	anterior=ant;
	siguiente=sig;
	}
	/**
	 * @return El siguiente nodo
	 */
	public DNodo<E> getSiguiente() {
		return siguiente;
	}
	/**
	 * @return El nodo anterior
	 */
	
	public DNodo<E> getAnterior(){
		return anterior;
	}
	/**
	 * Setea el rotulo del nodo
	 * @param elem rotulo del nodo
	 */
	public void setElement(E elem){ 
		elemento=elem;
	}
     /**
      * Setea el siguiente nodo
      * @param sig El nodo siguiente a setear
      */
	public void setSiguiente(DNodo<E> sig){
		siguiente=sig;
	}
	
	/**
     * Setea el nodo anterior
     * @param ant El nodo anterior a setear
     */
	public void setAnterior(DNodo<E> ant){
		anterior=ant;
	}

	/**
	 * Retorna el rotulo del nodo
	 */
	public E element() {
		
		return elemento;
	}
}