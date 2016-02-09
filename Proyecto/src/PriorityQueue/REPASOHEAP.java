package PriorityQueue;

import java.util.Comparator;

public class REPASOHEAP<K,V> implements PriorityQueue<K,V> {
	protected Entrada<K,V> [] elems;
	protected int n;
	protected Comparator<K> comp;
	
	public REPASOHEAP(Comparator<K> c, int max){
		comp = c;
		n = 0;
		elems = (Entrada<K,V>[]) new Entrada[max];
	}
	
	
	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return n == 0;
	}

	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if(isEmpty())
			throw new EmptyPriorityQueueException("");
		return elems[1];
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key == null){
			throw new InvalidKeyException("");
		}
		n++;
		Entrada<K,V> nuevo = new Entrada<K,V>(key,value);
		elems[n]= nuevo;
		int i = n;
		boolean seguir = true;
		while (i>1 && seguir){
			int posActual = i;
			int posPadre = i/2;
			if(comp.compare(elems[posActual].getKey(), elems[posPadre].getKey()) < 0){
				Entrada<K,V> aux = elems[posActual];
				elems[posActual] = elems[posPadre];
				elems[posPadre] = aux;
				i= i/2;
			}
			else
				seguir = false;			
		}
		return nuevo;		
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		Entry<K,V> minimo = min();
		if(n == 1){
			elems[1]= null;
			n--;
			return minimo;
		}
		else{
			elems[1] = elems[n];
			elems[n] = null;
			n--;
			int i = 1;
			boolean seguir = true;
			while(seguir){
				int posHI = 2*i;
				int posHD = 2*i +1;
				boolean tieneHI = posHI <= n;
				boolean tieneHD = posHD <= n;
				if(!tieneHI)
					seguir = false;
				else{
					int m;
					if(!tieneHD)
						m= posHI;
					else{
						if(comp.compare(elems[posHI].getKey(),elems[posHD].getKey()) < 0)
							m = posHI;
						else
							m = posHD;
					}
					if(comp.compare(elems[m].getKey(), elems[i].getKey()) < 0){
						Entrada<K,V> auxiliar = elems[m];
						elems[m] = elems[i];
						elems[i] = auxiliar;
						i = m;
					}	
					else{
						seguir = false;
					}
				}
			}
			
		}
		return minimo;
	}
	
	private void heapSort() throws InvalidKeyException, EmptyPriorityQueueException{
		REPASOHEAP<K,V> r = new REPASOHEAP<K,V>(new Comparador<K>() , size());
		for(int i = 0; i < n ; i++){
			r.insert(elems[i].getKey(),elems[i].getValue());
		}
		for(int i = 0; i < n ; i++){
			Entrada<K,V> ent = (Entrada<K,V>) r.removeMin();
			elems[i] = ent;
		}
	};
}
