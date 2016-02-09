package TDAGrafoNo;


import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

public class GrafoNoDir<V,E> implements Graph<V,E> {
	private PositionList<Vertice<V,E>> vertices;
	private PositionList<Arco<V,E>> arcos;
	
	public GrafoNoDir(){
		arcos = new ListaDoblementeEnlazada<Arco<V,E>>();
		vertices = new ListaDoblementeEnlazada<Vertice<V,E>>();
	}	
	
	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista =	new ListaDoblementeEnlazada<Vertex<V>>();
		for( Vertex<V> v : vertices )
		 		lista.addLast(v);
		return lista;
	}
	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>>();
		for(Edge<E> e : arcos)
			lista.addLast(e);
		return lista;
	}
	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v)
			throws InvalidVertexException {
		Vertice <V,E> w = checkVertex(v);
		PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>>();
		for(Edge<E> e : w.getAdyacentes())
			lista.addLast(e);
		return lista;
	}
	

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
			throws InvalidVertexException, InvalidEdgeException {
		Arco<V,E> arco = checkEdge(e);
		Vertice<V,E> v1 = checkVertex(v);
		if(arco.getV1() == v1)
			return arco.getV2();
		else{
			if(arco.getV2() == v1){
				return arco.getV1();
			}
			else
				return null;
	 }
	}
	@Override
	public Vertex<V>[] endVertices(Edge<E> e) throws InvalidEdgeException {
		Vertex<V>[] ver = (Vertex<V>[]) new Vertice[2];
		Arco<V,E> arco = checkEdge(e);
		ver[0] = arco.getV1();
		ver[1] = arco.getV2();
		return ver;
	}
	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w)
			throws InvalidVertexException {
		Vertice<V,E> vv = checkVertex(v);
		checkVertex(w);
		for(Edge<E> e : vv.getAdyacentes())
			try {
				if(opposite(v,e) == w)
					return true;
			} catch (InvalidEdgeException e1) {
				System.out.println("Arco invalido");
			}
		return false;
	}
	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		Vertice <V,E> w = checkVertex(v);
		V devolver = v.element();
		w.setElement(x);
		return devolver;
	}
	@Override
	public E replace(Edge<E> e, E x) throws InvalidEdgeException {
		Arco<V,E> arco = checkEdge(e);
		E devolver = e.element();
		arco.setElement(x);
		return devolver;
	}
	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> v = new Vertice<V,E>(x);
		vertices.addLast(v);
		try {
			v.setPosLV(vertices.last());
		} catch (EmptyListException e) {
			System.out.println("Lista vacia");
		}
		return v;
	}
	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E x)
			throws InvalidVertexException {
		Vertice<V,E> vv = checkVertex(v);
		Vertice<V,E> ww = checkVertex(w);
		Arco<V,E> arco = new Arco<V,E>(vv,ww,x);
		arcos.addLast(arco);
		vv.getAdyacentes().addLast(arco);
		ww.getAdyacentes().addLast(arco);
		
		try {
			arco.setPosLA(arcos.last());
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			arco.setPosV1(vv.getAdyacentes().last());
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			arco.setPosV2(ww.getAdyacentes().last());
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return arco;
	}
	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> vv = checkVertex(v);
		for(Edge<E> e : vv.getAdyacentes())
			try {
				removeEdge(e);
			} catch (InvalidEdgeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		vertices.remove(vv.getPosLV());
		V devolver = v.element();
		vv = null;
		return devolver;
	}
	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> arco = checkEdge(e);
		try {
			arcos.remove(arco.getPosLA());
		} catch (InvalidPositionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(arco.getV1() !=null && arco.getV2() !=null){
			try {
				arco.getV1().getAdyacentes().remove(arco.getPosV1());
			} catch (InvalidPositionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				arco.getV2().getAdyacentes().remove(arco.getPosV2());
			} catch (InvalidPositionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			arco = null;
		}
		return e.element();
	}
	
	private Vertice<V, E> checkVertex(Vertex<V> v) throws InvalidVertexException {
		if(v == null)
			throw new InvalidVertexException("Vertex nulo");
		try{
			return (Vertice<V,E>) v;
		} catch (ClassCastException e){
			
			System.out.println("Casteo imposible a vertice");
			return null;
		}		
	}
	
	private Arco<V,E> checkEdge(Edge<E> e) throws InvalidEdgeException{
		if(e== null)
			throw new InvalidEdgeException("Edge invalido");
		try{
			return (Arco<V,E>) e;
		} catch (ClassCastException e1){
			System.out.println("Imposible castear a arco");
			return null;
		}
	}
}
