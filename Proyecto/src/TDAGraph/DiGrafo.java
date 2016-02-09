package TDAGraph;

import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;
import TDAMapeo.InvalidKeyException;
import TDAQueue.ColaEnlazada;
import TDAQueue.EmptyQueueException;
import TDAQueue.Queue;

public class DiGrafo<E> implements Graph<E> {
	
	/**
	 * Lista de vertices
	 */
	private PositionList<Vertice<E>> vertices;
	
	/**
	 * Lista de arcos
	 */
	private PositionList<Arco<E>> arcos;
	
	/**
	 * Constructor del grafo, inicializa las dos listas.
	 */
	public DiGrafo(){
		arcos = new ListaDoblementeEnlazada<Arco<E>>();
		vertices = new ListaDoblementeEnlazada<Vertice<E>>();
	}
	
	
	
	public Iterable<Vertex> vertices() {
		PositionList<Vertex> lista = new ListaDoblementeEnlazada<Vertex> ();
		for (Vertex v : vertices)
			lista.addLast(v);
		return lista;
	}

	
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>> ();
		for (Edge<E> e : arcos)
			lista.addLast(e);
		return lista;
	}

	
	public Iterable<Edge<E>> incidentEdges(Vertex v)throws InvalidVertexException {
		Vertice<E> w = checkVertex(v);
		PositionList<Edge<E>> lista = new ListaDoblementeEnlazada <Edge<E>>();
		for(Edge<E> e : w.getIncidentes())
			lista.addLast(e);
		return lista;
	}

	public Iterable<Edge<E>> emergentEdges(Vertex v)throws InvalidVertexException {
		Vertice<E> w = checkVertex(v);
		PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>>();
		for(Edge<E> e : w.getEmergentes())
			lista.addLast(e);
		return lista;
	}


	public Vertex opposite(Vertex v, Edge<E> e)	throws InvalidVertexException, InvalidEdgeException {
		Vertice<E> w = checkVertex(v);
		Arco<E> a = checkEdge(e);
		if(a.getPredecesor() == w)
			return a.getSucesor();
		else
			return null;
	}


	public Vertex[] endVertices(Edge<E> e) throws InvalidEdgeException {
		Vertex [] a = (Vertex []) new Vertice[2];
		Arco<E> arco = checkEdge(e);
		a[0] = arco.getPredecesor();
		a[1] = arco.getSucesor();
		return a;
	}


	public boolean areAdjacent(Vertex v, Vertex w)throws InvalidVertexException {
		Vertice<E> vv = checkVertex(v);
		for(Edge<E> e : vv.getEmergentes())
			try {
				if(opposite(v,e) == w)
					return true;
			} catch (InvalidEdgeException e1) {
				System.out.println("Vertex invalido");
			}
		for(Edge<E> e : vv.getIncidentes())
			try {
				if(opposite(w,e) == v)
					return true;
			} catch (InvalidEdgeException e1) {
				System.out.println("Vertex invalido");
			}
		return false;
	}


	public int replace(Vertex v, int x) throws InvalidVertexException {
		Vertice<E> w = checkVertex(v);
		int devolver = w.element();
		w.setElement(x);
		return devolver;
	}


	public E replace(Edge<E> e, E x) throws InvalidEdgeException {
		Arco<E> arco = checkEdge(e);
		E devolver = arco.element();
		arco.setElement(x);
		return devolver;
	}

	
	public Vertex insertVertex(int x) {
		Vertice <E> nuevo = new Vertice<E>(x);
		vertices.addLast(nuevo);
		try {
			nuevo.setPosLV(vertices.last());
		} catch (EmptyListException e) {
			System.out.println("Lista vacia");
		}		
		return nuevo;
	}

	
	public Edge<E> insertEdge(Vertex v, Vertex w, E x) throws InvalidVertexException {
		Vertice <E> v1 = checkVertex(v);
		Vertice <E> v2 = checkVertex(w);
		Arco <E> arco = new Arco<E>(x,v1,v2);
		v1.getEmergentes().addLast(arco);
		v2.getIncidentes().addLast(arco);
		arcos.addLast(arco);
		try{
		arco.setPosEnArcos(arcos.last());
		}catch (EmptyListException e1){
			System.out.println("Lista de arcos vacia");
		}
		try{
		arco.setPosEnSaliente(v1.getEmergentes().last());
		}catch (EmptyListException e1){
			System.out.println("Lista de arcos emergentes vacia");
		}
		try{
		arco.setPosEnEntrante(v2.getIncidentes().last());
		}catch (EmptyListException e1){
			System.out.println("Lista de arcos salientes vacia");
		}
		return arco;
				
	}

	
	public Edge<E> insertDirectedEdge(Vertex v, Vertex w, E x)	throws InvalidVertexException {
		Vertice <E> v1 = checkVertex(v);
		Vertice <E> v2 = checkVertex(w);
		Arco <E> arco = new Arco<E>(x,v1,v2);
		v1.getEmergentes().addLast(arco);
		v2.getIncidentes().addLast(arco);
		arcos.addLast(arco);
		try{
		arco.setPosEnArcos(arcos.last());
		}catch (EmptyListException e1){
			System.out.println("Lista de arcos vacia");
		}
		try{
		arco.setPosEnSaliente(v1.getEmergentes().last());
		}catch (EmptyListException e1){
			System.out.println("Lista de arcos emergentes vacia");
		}
		try{
		arco.setPosEnEntrante(v2.getIncidentes().last());
		}catch (EmptyListException e1){
			System.out.println("Lista de arcos salientes vacia");
		}
		return arco;
	}
	public int removeVertex(Vertex v) throws InvalidVertexException {
		Vertice<E> w = checkVertex(v);
		int retornar = w.element();
		for(Edge<E> e : w.getEmergentes())
			try {
				removeEdge(e);
			} catch (InvalidEdgeException e1) {
				System.out.println("Edge a remover de lista de arcos emergentes invalida");
			}
		for(Edge<E> e : w.getIncidentes())
			try {
				removeEdge(e);
			} catch (InvalidEdgeException e1) {
				System.out.println("Edge a remover de lista de arcos emergentes invalida");
			}
		try {
			vertices.remove(w.getPosLV());
		} catch (InvalidPositionException e1) {
			System.out.println("Posicion a remover de lista de vertices invalida");
		}
		
		return retornar;
	}


	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<E> arco = checkEdge(e);
		E retornar = arco.element();
		Vertice<E> v1 = arco.getPredecesor();
		Vertice<E> v2 = arco.getSucesor();
		try {
			v1.getEmergentes().remove(arco.getPosEnSaliente());
		} catch (InvalidPositionException e1) {
			System.out.println("Posicion a remover de la lista de arcos emergentes vacia");
		}
		try {
			v2.getIncidentes().remove(arco.getPosEnEntrante());
		} catch (InvalidPositionException e1) {
			System.out.println("Posicion a remover de la lista de arcos entrantes vacia");
		}
		try {
			arcos.remove(arco.getPosEnArco());
		} catch (InvalidPositionException e1) {
			System.out.println("Posicion a remover de la lista de arcos");
		}
		return retornar;
	}
	
	/**
	 * Dado un vertex, devuelve dicho vertex casteado a vertice
	 * @param v Vertex a castear
	 * @return El vertex pasado por parametros casteado a Vertice
	 * @throws InvalidVertexException Si el vertex es nulo
	 */
	private Vertice<E> checkVertex(Vertex v) throws InvalidVertexException{
		if(v == null)
			throw new InvalidVertexException("Vertex nulo");
		try{
			return (Vertice<E>) v;
		}catch (ClassCastException e){
			throw new InvalidVertexException("No es un vertice");
	 }
	}
	/**
	 * Dado un edge, retorna el mismo edge casteado a Arco
	 * @param e Edge a castear
	 * @return El edge casteado a arco
	 * @throws InvalidEdgeException Si el edge es nulo
	 */
	private Arco<E> checkEdge(Edge<E> e) throws InvalidEdgeException{
		if(e == null)
			throw new InvalidEdgeException("Vertex nulo");
		try{
			return (Arco<E>) e;
		}catch (ClassCastException exp){
			throw new InvalidEdgeException("No es un vertice");
	 }
	}
	
	
	
	public void BFSshell(){
		Object ESTADO = new Object();
		Object VISITADO = new Object();
		Object NOVISITADO = new Object();
		
		try{
			for(Vertice<E> v : this.vertices){
				v.put(ESTADO, NOVISITADO);
			}
			for(Vertice<E> v : this.vertices){
				if(v.get(ESTADO) == NOVISITADO)
					bfs(v,ESTADO,VISITADO,NOVISITADO);
			}
		}catch(InvalidKeyException e){
			
		}
		
	}



	private void bfs(Vertice<E> v, Object ESTADO, Object VISITADO, Object NOVISITADO) {
		Queue <Vertice<E>> cola = new ColaEnlazada<Vertice<E>>();	
		cola.enqueue(v);
		try{
			while(!cola.isEmpty()){
				Vertice<E> w = this.checkVertex(cola.dequeue());
				System.out.println(w.element());
				w.put(ESTADO, VISITADO);
				for(Arco<E> x : w.getEmergentes())
					if(x.getSucesor().get(ESTADO) == NOVISITADO)
						cola.enqueue(x.getSucesor());
			}
		}catch(InvalidKeyException e){
			
		}catch(InvalidVertexException e){
			
		}catch(EmptyQueueException e){
			
		}
	}
	
	public void DFSshell(){
		Object VISITADO = new Object();
		Object NOVISITADO = new Object();
		Object ESTADO = new Object();
		try{
			for(Vertice<E> v : this.vertices){
				v.put(ESTADO, NOVISITADO); 
			}
			for(Vertice<E> v : this.vertices){
				if(v.get(ESTADO)==NOVISITADO)
					dfs(v,ESTADO,VISITADO,NOVISITADO);
			}			
		}catch(InvalidKeyException e){
			
		}
	}
	
	private void dfs(Vertice<E> v, Object ESTADO, Object VISITADO, Object NOVISITADO) {
		try{
			v.put(ESTADO, VISITADO);
			System.out.println(v.element());
			
			for(Arco<E> e: v.getEmergentes()){
				if(e.getSucesor().get(ESTADO)==NOVISITADO)
					dfs(e.getSucesor(),ESTADO,VISITADO,NOVISITADO);
			}
		}catch(InvalidKeyException e){
			
		}
	}



	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}