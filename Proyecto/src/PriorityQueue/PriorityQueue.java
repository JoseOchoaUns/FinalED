package PriorityQueue;

public interface PriorityQueue<K,V>{
	
	public int size();
	public boolean isEmpty();
	public Entry<K,V> min() throws	EmptyPriorityQueueException;
	public Entry<K,V> insert(K key, V value) throws	InvalidKeyException;
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException;
}

