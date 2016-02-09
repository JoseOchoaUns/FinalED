package TDAABBB2;

import java.util.Comparator;

import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;
import TDAMapeo.Entrada;
import TDAMapeo.Entry;
import TDAMapeo.InvalidKeyException;
import TDAMapeo.Map;




public class ABB13<K extends Comparable<K>,V> implements Map<K,V> {
	protected int n;
	protected NodoABB<K,V> raiz;
	protected Comparator<K> comp;
	public ABB13(Comparator<K> c){
		comp = c;
		raiz = new NodoABB<K,V>(null,null);
		n = 0;
	}
	
	
	private NodoABB<K,V> buscar(K e){
		return buscarAux(e,raiz);
	}
	
	private NodoABB<K, V> buscarAux(K e, NodoABB<K, V> nodo) {
		if(nodo.getEntrada()==null){
			return nodo;
		}
		else{
			int c = comp.compare(e, nodo.getEntrada().getKey());
			if(c==0){
				return nodo;
			}
			else{
				if(c<0)
					return buscarAux(e,nodo.getIzq());
				else
					return buscarAux(e,nodo.getDer());
			}
		}		
	}
	
	private V insertar(Entrada<K,V> e){
		NodoABB<K,V> nodo = buscar(e.getKey());
		if(nodo.getEntrada() == null){
			nodo.setEntrada(e);
			nodo.setIzq(new NodoABB<K,V>(null,nodo));
			nodo.setDer(new NodoABB<K,V>(null,nodo));
			n++;
			return null;
		}
		else{
			V devolver = nodo.getEntrada().getValue();
			nodo.setEntrada(e);
			nodo.setIzq(new NodoABB<K,V>(null,nodo));
			nodo.setDer(new NodoABB<K,V>(null,nodo));
			return devolver;
		}
	}
	
	
	public V eliminar(K k){
		NodoABB<K,V> nodo = buscar(k);
		if(nodo.getEntrada() != null){
			V devolver = nodo.getEntrada().getValue();
			if(nodo == raiz)
				eliminarRaiz();
			else
				eliminarAux(nodo);
			n--;
			return devolver;
			
		}
		else
			return null;
	}
	

	private void eliminarAux(NodoABB<K, V> p) {
		if( isExternal(p) ) { // p es hoja
			p.setEntrada( null ); 
			p.setIzq( null ); 
			p.setDer( null );
		} else { // p no es hoja
			if( soloHI(p) ) {
				// Engancho al padre de p con el hijo izquierdo de p
				if( p.getPadre().getIzq() == p )
					p.getPadre().setIzq( p.getIzq() );
				else
					p.getPadre().setDer( p.getIzq() );
			p.getIzq().setPadre( p.getPadre() );
			}else
				 if( soloHD(p) ){// Engancho al padre de p con el hijo derecho de p
					if( p.getPadre().getIzq() == p )
						p.getPadre().setIzq( p.getDer() );
					else
				p.getPadre().setDer( p.getDer() );
				p.getDer().setPadre( p.getPadre() );
				} else // p tiene dos hijos: seteo como rótulo de p al rótulo del sucesor inorder de p.
					p.setEntrada( eliminarMinimo( p.getDer() ) );
		}
	}

	private void eliminarRaiz() {
		if(isExternal(raiz)){
			raiz.setEntrada(null);
			raiz.setIzq(null);
			raiz.setDer(null);
		}
		else
			if(soloHI(raiz)){
				raiz= raiz.getIzq();
				raiz.setPadre(null);
			}
			else
				if(soloHD(raiz)){
					raiz= raiz.getDer();
					raiz.setPadre(null);
				}
				else
					raiz.setEntrada(eliminarMinimo(raiz.getDer()));
	}
		
		
	private Entrada<K, V> eliminarMinimo(NodoABB<K, V> p) {
		if( p.getIzq().getEntrada() == null ) {
			Entrada<K,V> aRetornar = p.getEntrada();
		if( p.getDer().getEntrada() == null ) { // p es hoja
			p.setEntrada( null );
		    p.setIzq( null );
		    p.setDer( null );
		} 
		else { // p solo tiene hijo derecho (xq no tiene izquierdo)
			if (p.getPadre().getDer()==p)
				p.getPadre().setDer( p.getDer() );
			else
				p.getPadre().setIzq( p.getDer() );
			p.getDer().setPadre( p.getPadre() );
		}
			return aRetornar;
		} else
			return eliminarMinimo( p.getIzq() );
	}

	/*
	 * private E eliminarMinimo( NodoABB<E> p ) {
		if( p.getIzq().getEntrada() == null ) {
			Entrada<K,V> aRetornar = p.getEntrada();
		if( p.getDer().getEntrada() == null ) { // p es hoja
			p.setRotulo( null );
		    p.setIzq( null );
		    p.setDer( null );
		} 
		else { // p solo tiene hijo derecho (xq no tiene izquierdo)
			if (p.getPadre().getDer()==p)
				p.getPadre().setDer( p.getDer() );
			else
				p.getPadre().setIzq( p.getDer() );
			p.getDer().setPadre( p.getPadre() );
		}
		return aRetornar;
		} else
		return eliminarMinimo( p.getIzq() );
		}
	 */
	


	private boolean soloHD(NodoABB<K,V> p) {
		return p.getIzq().getEntrada() == null && p.getDer().getEntrada() != null;
	}
	private boolean soloHI(NodoABB<K,V> p) {
		return p.getIzq().getEntrada() != null && p.getDer().getEntrada() == null;
	}
	private boolean isExternal(NodoABB<K,V> p) {
		return p.getIzq().getEntrada() == null && p.getDer().getEntrada() == null;
	}



	@Override
	public int size() {
		// TODO Auto-generated method stub
		return n;
	}



	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return n==0;
	}



	@Override
	public V get(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("Clave nula papa");
		
		NodoABB<K,V> nodo = buscar(key);
		
		return nodo.getEntrada() == null ? null : nodo.getEntrada().getValue() ;
	}



	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("Clave nula papa");
		
		V devolver = insertar(new Entrada<K,V>(key,value));
		return devolver;
	}



	@Override
	public V remove(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("Clave nula papa");
		V devolver = eliminar(key);
		return devolver;
	}



	@Override
	public Iterable<K> keys() {
		PositionList<K> devolver = new ListaDoblementeEnlazada<K>();
		if(raiz.getEntrada() != null)
			crearIterableK(devolver,raiz);
		return devolver;
	}



	private void crearIterableK(PositionList<K> devolver, NodoABB<K, V> nodo) {
		devolver.addLast(nodo.getEntrada().getKey());
		if(nodo.getIzq().getEntrada() != null)
			crearIterableK(devolver,nodo.getIzq());
		if(nodo.getDer().getEntrada() != null)
			crearIterableK(devolver,nodo.getDer());
	}


	@Override
	public Iterable<V> values() {
		PositionList<V> devolver = new ListaDoblementeEnlazada<V>();
		if(raiz.getEntrada() != null)
			crearIterableV(devolver,raiz);
		return devolver;
	}



	private void crearIterableV(PositionList<V> devolver, NodoABB<K, V> nodo) {
		devolver.addLast(nodo.getEntrada().getValue());
		if(nodo.getIzq().getEntrada() != null)
			crearIterableV(devolver,nodo.getIzq());
		if(nodo.getDer().getEntrada() != null)
			crearIterableV(devolver,nodo.getDer());
		
	}


	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> devolver = new ListaDoblementeEnlazada<Entry<K,V>>();
		if(raiz.getEntrada() != null)
			crearIterable(devolver,raiz);
		return devolver;
	}


	private void crearIterable(PositionList<Entry<K, V>> devolver,
			NodoABB<K, V> nodo) {
		devolver.addLast(nodo.getEntrada());
		if(nodo.getIzq().getEntrada() != null)
			crearIterable(devolver,nodo.getIzq());
		if(nodo.getDer().getEntrada() != null)
			crearIterable(devolver,nodo.getDer());
		
	}
}