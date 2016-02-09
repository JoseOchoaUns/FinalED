package TDALista;

import java.util.Iterator;

/**
 * 
 * @author Comision 2: Franco Raniolo y Jos� Ochoa
 *
 * 
 */
public interface PositionList<E> extends Iterable <E> {
	/**
	 * Retorna el tama�o de la lista
	 * @return Tama�o de la lista
	 */
	public int size();
	
	/**
	 * Metodo para saber si una lista esta vacia
	 * @return Falso si tiene uno o mas elementos, Verdadero si no tiene elementos
	 */
	public boolean isEmpty();
	
	/**
	 * Metodo que retorna la posicion del primer elemento de la lista
	 * @return Posicion del primero elemento de lista
	 * @throws EmptyListException Lanza excepcion si se invoca al metodo a una lista vacia
	 */
	public Position <E> first() throws EmptyListException;
	
	/**
	 * Metodo que retorna la posicion del ultimo elemento de la lista
	 * @return Posicion del ultimo elemento de lista
	 * @throws EmptyListException Lanza excepcion si se invoca al metodo a una lista vacia
	 */
	public Position <E> last() throws EmptyListException;
	
	/**
	 * Metodo que retorna la posicion del proximo elemento de la posicion pasada por parametro
	 * @param p Posicion pasada por parametro
	 * @return Retorna la posicion del elemento proximo a la posicion pasada por parametro
	 * @throws InvalidPositionException Lanza excepcion si la posicion pasada por parametro es nula o es imposible de castear a Nodo o si la lista es vacia
	 * @throws BoundaryViolationException Lanza excepcion si la posicion pasada por parametro es la posicion del ultimo elemento de la lista
	 */
	public Position <E> next(Position <E> p) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Metodo que retorna la posicion del elemento anterior a la posicion pasada por parametro
	 * @param p Posicion pasada por parametro
	 * @return Retorna la posicion del elemento anteriro a la posicion pasada por parametro
	 * @throws InvalidPositionException Lanza excepcion si la posicion pasada por parametro es nula o es imposible de castear a Nodo o si la lista es vacia
	 * @throws BoundaryViolationException Lanza excepcion si la posicion pasada por parametro es la posicion del primer elemento de la lista
	 */
	public Position <E> prev(Position <E> p) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * A�ade el elemento pasado por parametro en la primera posicion de la lista
	 * @param e Elemento a a�adir primero
	 */
	public void addFirst(E e);
	
	/**
	 * A�ade el elemento pasado por parametro en la ultima posicion de la lista
	 * @param e Elemento a a�adir ultimo
	 */
	public void addLast(E e);
	
	/**
	 * Metodo que crea un nuevo nodo con rotulo e y lo agrega despues de la posicion pasada por parametro
	 * @param p Posicion pasada por parametro
	 * @param e Rotulo del nuevo nodo
	 * @throws InvalidPositionException Lanza excepcion si la posicion pasada por parametro es nula o es imposible de castear a Nodo o si la lista es vacia
	 */
	public void addAfter(Position <E> p, E e) throws InvalidPositionException;
	
	/**
	 * Metodo que crea un nuevo nodo con rotulo e y lo agrega adelante de la posicion pasada por parametro
	 * @param p Posicion pasada por parametro
	 * @param e Rotulo del nuevo nodo
	 * @throws InvalidPositionException Lanza excepcion si la posicion pasada por parametro es nula o es imposible de castear a Nodo o si la lista es vacia
	 */
	public void addBefore(Position <E> p, E e) throws InvalidPositionException;
	
	/** 
	 * Metodo que dada una posicion se elimina de la lista y retorna el rotulo del nodo que estaba en esa posicion
	 * @param p Posicion del elemento a eliminar
	 * @return Rotulo de la posicion removida
	 * @throws InvalidPositionException Lanza excepcion si la posicion pasada por parametro es nula o es imposible de castear a Nodo o si la lista es vacia
	 */
	public E remove (Position<E> p) throws InvalidPositionException;
	
	/**	
	 * Dada una posicion, reemplaza el elemento de la posicion por el elemento pasado por parametro. Devuelve el elemento viejo
	 * @param p Posicion a establecer el elemento
	 * @param e Elemento para reemplazar
	 * @return Viejo elemento
	 * @throws InvalidPositionException Lanza excepcion si la posicion pasada por parametro es nula o es imposible de castear a Nodo o si la lista es vacia	 
	 */
	public E set(Position <E> p, E e) throws InvalidPositionException;
	
	/**
	 * Devuelve una colecci�n iterable de posiciones.
	 * @return Una colecci�n iterable de posiciones.
	 */
	public Iterable<Position<E>> positions();
	
	/**
	 * Devuelve un un iterador de todos los elementos de la lista.
	 * @return Un iterador de todos los elementos de la lista.
	 */
	public Iterator<E> iterator();

}
