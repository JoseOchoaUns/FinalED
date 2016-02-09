package TDAQueue;



public interface Queue<E> {
	/**
	 * Inserta el elemento e al final de la cola
	 * @param e Elemento a agrega
	 */
	public void enqueue(E e);
	/**
	 * Elimina el elemento del frente de la cola y lo retorna.
	 * @return Elemento eliminado
	 * @throws EmptyQueueException Cuando se llama a este metodo con la cola vacia
	 */
	public E dequeue() throws EmptyQueueException;
	/**
	 * Retorna el elemento del frente de la cola.
	 * @return Elemento de la frente de la cola
	 * @throws EmptyQueueException Cuando se llama a este metodo con la cola vacia
	 */
	public E front() throws EmptyQueueException;
	/**
	 * 
	 * @return True si la cola esta vacia, False si la cola no esta vacia
	 */
	public boolean isEmpty();
	/**
	 * 
	 * @return Cantidad de elementos en la cola
	 */
	public int size();
	}
