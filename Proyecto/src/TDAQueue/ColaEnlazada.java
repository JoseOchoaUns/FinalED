package TDAQueue;



public class ColaEnlazada<E> implements Queue<E> {
	/*
	 * Frente de la cola
	 */
	protected Nodo<E> head;
	/*
	 * Ultimo elemento de la cola
	 */
	protected Nodo<E> tail;
	/*
	 * Tamaño de la cola
	 */
	protected int tamaño;
	/**
	 * Constructor de la cola. Inicializa la cola vacia;
	 */
	public ColaEnlazada(){
		tamaño = 0;
		head = new Nodo<E>();
		tail = new Nodo<E>();
	}
	
	public void enqueue( E e ) {
		Nodo<E> nodo = new Nodo<E>();
		nodo.setElemento( e );
		nodo.setNext( null );
		if (tamaño == 0 )
		head = nodo;
		else
		tail.setNext( nodo );
		tail = nodo;
		tamaño++;
		}
	
	public E dequeue() throws
		EmptyQueueException {
		if( tamaño == 0 )
			throw new EmptyQueueException( "cola vacia" );
		E tmp = head.getElemento();
		head = head.getNext();
		tamaño--;
		if( tamaño == 0 ) tail = null;
		return tmp;
	}

	public E front() throws EmptyQueueException {
		if( tamaño == 0 )
			throw new EmptyQueueException( "cola vacia" );
		
		return head.getElemento();
	}

	public boolean isEmpty() {
		return tamaño==0;
	}

	public int size() {
		return tamaño;
	}
}