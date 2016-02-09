package TDALista;

import java.util.Iterator;

public class ListaDoblementeEnlazada <E> implements PositionList<E> {
	/*
	 * Cantidad de elementos en la lista
	 */
	protected int cant;
	
	/*
	 * Nodo dummy de la lista
	 */
	protected DNodo<E> header, trailer;
	
	/** El constructor crea una lista vacia, O(1)*/
	public ListaDoblementeEnlazada(){
		cant = 0;
		header = new DNodo<E> (null, null,null);
		trailer = new DNodo<E>(null, header, null);
		header.setSiguiente(trailer);
	}

	
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}


	public int size() {
		return cant;
	}

	
	public boolean isEmpty() {
		return cant == 0;
	}


	public Position<E> first() throws EmptyListException {
		if(header.getSiguiente()== trailer)
			throw new EmptyListException("Lista Vacia");
		return header.getSiguiente();
	}


	public Position<E> last() throws EmptyListException {
		if(isEmpty())
			throw new EmptyListException("Lista vacia");
		return trailer.getAnterior();
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException,
			BoundaryViolationException {
		DNodo<E> n = checkPosition(p);
		if(n.getSiguiente() == trailer)
			throw new BoundaryViolationException("La posicion dada es la ultima");
		return n.getSiguiente();
	}


	public Position<E> prev(Position<E> p) throws InvalidPositionException,
			BoundaryViolationException {
		DNodo<E> n = checkPosition(p);
		if(n.getAnterior() == header)
			throw new BoundaryViolationException("La posicion dada es la ultima");
		return n.getAnterior();
	}


	public void addFirst(E e) {
		DNodo<E> nuevo = new DNodo<E>(e,header,header.getSiguiente());
		header.getSiguiente().setAnterior(nuevo);
		header.setSiguiente(nuevo);
		cant ++;
		
	}


	public void addLast(E e) {
		DNodo<E> nuevo = new DNodo<E>(e, trailer.getAnterior(), trailer);
		trailer.getAnterior().setSiguiente(nuevo);
		trailer.setAnterior(nuevo);
		cant++;
	}


	public void addAfter(Position<E> p, E e) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(e ,n, n.getSiguiente());
		n.getSiguiente().setAnterior(nuevo);
		n.setSiguiente(nuevo);
		cant++;
	}


	public void addBefore(Position<E> p, E e) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(e, n.getAnterior(), n);
		n.getAnterior().setSiguiente(nuevo);
		n.setAnterior(nuevo);
		cant++;		
	}


	public E remove(Position<E> p) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		E devolver = n.element();
		n.getAnterior().setSiguiente(n.getSiguiente());
		n.getSiguiente().setAnterior(n.getAnterior());
		n.setAnterior(null);
		n.setSiguiente(null);
		cant--;
		return devolver;
	}


	public E set(Position<E> p, E e) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		E devolver = n.element();
		n.setElement(e);
		return devolver;
	}
	/**
	 * Metodo privado que dada una posicion la castea a nodo
	 * @param p Posicion a castear
	 * @return La posicion pasada por parametro casteada a nodo 
	 * @throws InvalidPositionException Lanza excepcion si la posicion es nula, es el trailer, es el header o es imposible castearla a nodo
	 */
	private DNodo<E> checkPosition(Position <E> p) throws InvalidPositionException{
		
			if(p == null)
				throw new InvalidPositionException("Posicion nula");
			if(p == trailer)
				throw new InvalidPositionException("Trailer no es una posicion valida");
			if(p == header)
				throw new InvalidPositionException("Header no es una posicion valida");
			try{
				DNodo<E> n = (DNodo<E>) p;
				if(n.getAnterior() == null || n.getSiguiente() == null)
					throw new InvalidPositionException("La posicion no corresponde a una lista valida");
				return n;
			}catch (ClassCastException e){
				throw new InvalidPositionException("Posicion no valida para esta lista");
			}
	}



	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> P = new ListaDoblementeEnlazada<Position<E>>();
		try{	
			if(!isEmpty()){
				Position<E> p = first();
			while(p!=null){
				P.addLast(p);
				if(p == last())
					p= null;
				else
					p= next(p);
				}
			}
			}catch (EmptyListException e){
				
			}catch (BoundaryViolationException e){
				
			}catch (InvalidPositionException e) {
				
			}
	return P;
	}
}
