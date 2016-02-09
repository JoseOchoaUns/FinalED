package PriorityQueue;

import java.util.Comparator;

public class Heap<K,V> implements PriorityQueue<K,V>{
	protected Entrada<K,V> [] elems;
	protected Comparator<K> comp;
	protected int size;
	
	public Heap(int max, Comparator<K> comp){
		size = 0;
		this.comp= comp;
		elems = (Entrada<K,V>[]) new Entrada[max];
	}
	
	

	public int size(){
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if(isEmpty())
			throw new EmptyPriorityQueueException("Cola con prioridad vacia");
		return elems[1];
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key == null){
			throw new InvalidKeyException("Key nula");
		}
		Entrada<K,V> entrada = new Entrada<K,V>(key,value);
		size++;
		elems[size] = entrada;
		
		int i = size;
		boolean seguir = true;
		while(i>1 && seguir){
			Entrada<K,V> posPadre = elems[i/2];
			Entrada<K,V> posActual = elems[i];
			if(comp.compare(posActual.getKey(), posPadre.getKey()) < 0){
				Entrada<K,V> aux = elems[i/2];
				elems[i/2] = elems[i];
				elems[i] = aux;
				i = i/2;
			}
			else{
				seguir = false;
			}
		}
		return entrada;
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if(isEmpty())
			throw new EmptyPriorityQueueException ("Gil");
		Entry<K,V> retornar = min();
		if(size==1){
			elems[1]= null;
			size = 0;
			return retornar;
		}
		else{
			elems[1] = elems[size];
			elems[size] = null;
			size--;
			int i = 1;
			boolean seguir = true;
			while(seguir){
				int posHI, posHD;
				posHI = 2*i;
				posHD = 2*i+1;
				boolean tieneHI,tieneHD;
				tieneHI = (posHI <= size);
				tieneHD = (posHD <= size);			
			if(!tieneHI){
				seguir = false; // ES HOJA
			}
			else{
				int m;
				if(!(tieneHD))
					m= posHI;
				else{
					if(comp.compare(elems[posHI].getKey(), elems[posHD].getKey()) < 0)
						m = posHI;
					else
						m = posHD;
				}
				if(comp.compare(elems[m].getKey(), elems[i].getKey()) < 0){
					Entrada<K,V> aux = elems[m];
					elems[m] = elems[i];
					elems[i] = aux;
					i = m;
				}
				else
					seguir = false;
			}
			}
			return retornar;
		}

	}
	public void HeapSort() throws InvalidKeyException, EmptyPriorityQueueException{
		PriorityQueue<K,V> PQ = new Heap(size, new Comparador());
		for(int i = 1; i < size; i++)
			PQ.insert(elems[i].getKey(), elems[i].getValue());
		for(int i = 1; i < size; i++)
			elems[i] = (Entrada<K, V>) PQ.removeMin();
	}
}