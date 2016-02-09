package TDAGrafoNo;



public interface Graph<V,E> {
	
	public Iterable<Vertex<V>> vertices();
	
	public Iterable<Edge<E>> edges();
	
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException;
	
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException;
	
	public Vertex<V> [] endVertices(Edge<E> e) throws InvalidEdgeException;	
	
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException;
	
	public V replace( Vertex<V> v, V x) throws InvalidVertexException;
	
	public E replace( Edge<E> e, E x) throws InvalidEdgeException;
	
	public Vertex<V> insertVertex(V x);
	
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E x) throws InvalidVertexException;
	
	public V removeVertex(Vertex<V> v) throws InvalidVertexException;
	
	public E removeEdge(Edge<E> e) throws InvalidEdgeException;
}
