package TDADiccionario;

import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;
import TDAMapeo.Entry;
import TDAMapeo.InvalidKeyException;
import TDAMapeo.Entrada;

public class Diccionario <K,V> implements Dictionary<K,V> {
	
	protected int n, N= 1;
	protected PositionList<Entry<K,V>>[] A;
	
	public Diccionario(){
		n= 0;
		A = (PositionList<Entry<K,V>>[]) new ListaDoblementeEnlazada[N];
		for(int i = 0; i< A.length; i++){
			A[i] = new ListaDoblementeEnlazada<Entry<K,V>>();
		}
	}
	
	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return n == 0;
	}

	public int h(K key){
		return key.hashCode() % N;
	}
	
	public Entry<K, V> find(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("");
		for(Entry<K,V> ent : A[h(key)]){
			if(ent.getKey() == key)
				return ent;
		}
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("");
		PositionList<Entry<K,V>> lista = new ListaDoblementeEnlazada<Entry<K,V>>();
		for(Entry<K,V> ent : A[h(key)]){
			if(ent.getKey() == key)
				lista.addLast(ent);
		}
		return lista;
	}
	/*public Entry<K,V> insert(K k, V v ) {
		Entry<K,V> e = new Entrada<K,V>(k,v);
		D.addLast( e ); // Asumo que funciona en orden 1.
		return e;
} Tinsert(n) = O(1) si n = cantidad de ent*/
	
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		if(n/N > 0.5)
			reHash();
		Entrada<K,V> ent = new Entrada<K,V>(key,value);
		n++;
		A[h(key)].addLast(ent);
		return ent;
	}

	private void checkKey(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("");	
	}

	private void reHash() {
		PositionList<Entry<K,V>>[] viejo = A;
		N = N*2;
		A = (PositionList<Entry<K,V>>[]) new ListaDoblementeEnlazada[N];
		for(int i=0 ; i < A.length ; i++)
			A[i] = new ListaDoblementeEnlazada<Entry<K,V>>();
		for(int i = 0; i< viejo.length ; i ++){
			for(Entry<K,V> ent : viejo[i]){
				A[h(ent.getKey())].addLast(ent);
			}
		}
		
	}

	/*
	 * public Entry<K,V> remove( Entry<K,V> e ) throws NonExistentEntryException {
		for( Position<Entry<K,V>> p : D.positions() ) {
		if( p.element() == e ) { D.remove(p); return e; }
		}
		throw new NonExistentEntryException( “Diccionario::remove: …” ); return null;
	 */
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if(e == null)
			throw new InvalidEntryException("");
		
		for(Position<Entry<K, V>> pos : A[h(e.getKey())].positions()){
			if(pos.element()== e){
				try {
					A[h(e.getKey())].remove(pos);
					n--;
				} catch (InvalidPositionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return pos.element();
			}
		}
			throw new InvalidEntryException("");
			
	}
	
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> lista = new ListaDoblementeEnlazada<Entry<K,V>>();
		for(int i = 0; i < A.length ; i++){
			for(Entry<K,V> ent : A[i])
				lista.addLast(ent);
		}
		return lista;
	}

}
