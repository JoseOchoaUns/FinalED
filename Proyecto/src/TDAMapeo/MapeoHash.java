package TDAMapeo;

import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;
import TDALista.ListaDoblementeEnlazada;;

/**
 * 
 * @author Comision 2: Ochoa Jose y Franco Raniolo
 *
 * @param <K> Tipo KEY
 * @param <V> Tipo VALUE
 */
public class MapeoHash<K,V> implements Map<K,V> {
	/*
	 * Arreglo de buckets
	 */
	private PositionList<Entrada<K,V>>[] A;
	/*
	 * Cantidad de elementos en el mapeo
	 */
	private int n;
	/*
	 * Tamaño total del arreglo de buckets
	 */
	private static int N = 13;
	
	/**
	 * Constructor del mapeo : Inicializa el mapeo
	 */
	public MapeoHash(){
		n = 0;
		A = (PositionList<Entrada<K,V>>[]) new ListaDoblementeEnlazada[N];
		for(int i = 0; i < N; i++){
			A[i] = new ListaDoblementeEnlazada<Entrada<K,V>>();
		}
	}
	/*
	 * Dada una key, la codifica y retorna un entero con la posicion en la cual debe ir esa key en el arreglo
	 * @param key Key pasada por parametro
	 * @return Entero conseguido despues de codificar la key
	 */
	private int h (K key){
		return key.hashCode() % N;
	}
	
	
	public int size() {
		return n;
	}

	
	public boolean isEmpty() {
		return n == 0;
	}

	
	public V get(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("Key nula");
		for(Entrada<K,V> ent : A[h(key)]){
			if(ent.getKey() == key)
				return ent.getValue();
		}
		return null;
	}

	
	public V put(K key, V value) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("Key nula");
		if(n/N > 0.5 )
			reHash();
		for(Entrada<K,V> ent : A[h(key)])
			if(ent.getKey() == key ){
				V devolver = ent.getValue();
				ent.setValue(value);
				return devolver;
			}
		n ++;
		A[h(key)].addLast(new Entrada<K,V>(key,value));
		return null;
	}

	
	public V remove(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("Key nula");
		for(Position<Entrada<K,V>> pos : A[h(key)].positions())
			if(pos.element().getKey() == key){
				V devolver = pos.element().getValue();
				try {
					
					A[h(key)].remove(pos);
					n--;
				} catch (InvalidPositionException e) {
					System.out.println("Pos invalida");
				}
				return devolver;
			}				
		return null;
	}

	
	public Iterable<K> keys() {
		PositionList<K> lista = new ListaDoblementeEnlazada<K>();
		for(int i = 0; i < A.length ; i++){
			if(!A[i].isEmpty())
				for(Entrada<K,V> ent : A[i])
					lista.addLast(ent.getKey());
		}
		
		return lista;
	}

	
	public Iterable<V> values() {
		PositionList<V> lista = new ListaDoblementeEnlazada<V>();
		for(int i = 0; i < A.length ; i++){
			if(!A[i].isEmpty())
				for(Entrada<K,V> ent : A[i])
					lista.addLast(ent.getValue());
		}
		
		return lista;
	}


	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> lista = new ListaDoblementeEnlazada<Entry<K,V>>();
		for(int i = 0; i < A.length ; i++){
			if(!A[i].isEmpty())
				for(Entrada<K,V> ent : A[i])
					lista.addLast(ent);
		}		
		return lista;
	}
	/*
	 * Cuando el factor de carga del mapeo supera el valor 0,5 se crea un nuevo 
	 * arreglo con el doble del tamaño del original en el cual se reubicaran denuevo todas
	 * las entradas. Luego ese arreglo de position list sera el nuevo mapeo.
	 */
	private void reHash(){
		N = N*2;
		PositionList<Entrada<K,V>>[] viejo = A;
		A = (PositionList<Entrada<K,V>> []) new ListaDoblementeEnlazada[N];
		for(int i = 0; i < N; i++){
			A[i] = new ListaDoblementeEnlazada<Entrada<K,V>>();
		}
		for(int i = 0; i < viejo.length ; i++){
			if(!viejo[i].isEmpty())
				for(Entrada<K,V> ent : viejo[i])
					A[h(ent.getKey())].addLast(ent);
		}
	}
}
