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
	 * Tama�o de la cola
	 */
	protected int tama�o;
	/**
	 * Constructor de la cola. Inicializa la cola vacia;
	 */
	public ColaEnlazada(){
		tama�o = 0;
		head = new Nodo<E>();
		tail = new Nodo<E>();
	}
	
	public void enqueue( E e ) {
		Nodo<E> nodo = new Nodo<E>();
		nodo.setElemento( e );
		nodo.setNext( null );
		if (tama�o == 0 )
		head = nodo;
		else
		tail.setNext( nodo );
		tail = nodo;
		tama�o++;
		}
	
	public E dequeue() throws
		EmptyQueueException {
		if( tama�o == 0 )
			throw new EmptyQueueException( "cola vacia" );
		E tmp = head.getElemento();
		head = head.getNext();
		tama�o--;
		if( tama�o == 0 ) tail = null;
		return tmp;
	}

	public E front() throws EmptyQueueException {
		if( tama�o == 0 )
			throw new EmptyQueueException( "cola vacia" );
		
		return head.getElemento();
	}

	public boolean isEmpty() {
		return tama�o==0;
	}

	public int size() {
		return tama�o;
	}
}