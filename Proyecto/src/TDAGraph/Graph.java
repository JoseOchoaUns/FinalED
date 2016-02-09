package TDAGraph;


/**
 * 
 * @author Comision 2 : Jose Ochoa, Franco Raniolo
 *
 */
public interface Graph<E> {
	
	/**
	 * Retorna un iterable de vertices del grafo
	 * @return Iterable de vertices
	 */
	public Iterable<Vertex> vertices();
	
	/**
	 * Retorna un iterable de arcos del grafo
	 * @return Iterable de arcos
	 */
	public Iterable<Edge<E>> edges();
	
	/**
	 * Retorna un iterable de arcos incidentes de un determinado vertice
	 * @param v Vertice del cual se veran sus arcos incidentes
	 * @return Iterable de arcos incidentes de un determinado vertice
	 * @throws InvalidVertexException Si el vertice pasado por parametro es invalido
	 */
	public Iterable<Edge<E>> incidentEdges(Vertex v) throws InvalidVertexException;
	
	/**
	 * Retorna un iterable de arcos emergentes de un determinado vertice
	 * @param v Vertice del cual se veran sus arcos emergentes
	 * @return Iterable de arcos emergentes de un determinado vertice
	 * @throws InvalidVertexException Si el vertice pasado por parametro es invalido
	 */
	public Iterable<Edge<E>> emergentEdges(Vertex v) throws InvalidVertexException;
	
	/**
	 * Dado un arco y un vertice retorna el vertice opuesto
	 * @param v Vertice pasado por parametro
	 * @param e Arco pasado por parametro
	 * @return Vertice opuesto
	 * @throws InvalidVertexException Vertice invalido
	 * @throws InvalidEdgeException Arco invalido
	 */
	public Vertex opposite(Vertex v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException;
	
	/**
	 * Dado un arco retorna un arreglo con los 2 vertices unidos por dicho arco
	 * @param e Arco del cual se veran sus vertices
	 * @return Los vertices del arco pasado por parametro
	 * @throws InvalidEdgeException Arco invalido
	 */
	public Vertex [] endVertices(Edge<E> e) throws InvalidEdgeException;
	/**
	 * Retorna true si dos vertices son adyacentes, false en caso contrario
	 * @param v Vertice 1
	 * @param w	Vertice 2
	 * @return True si dos vertices son adyacentes, false en caso contrario
	 * @throws InvalidVertexException Si algun vertice es invalido
	 */
	public boolean areAdjacent(Vertex v, Vertex w) throws InvalidVertexException;
	
	/**
	 * Dado un vertice y un rotulo, reemplaza el rotulo actual por el pasado por parametros retornando el valor viejo
	 * @param v Vertice
	 * @param x Nuevo rotulo
	 * @return Rotulo viejo
	 * @throws InvalidVertexException Vertice invalido
	 */
	public int replace( Vertex v, int x) throws InvalidVertexException;
	
	/**
	 * Dado un arco y un rotulo, reemplaza el rotulo actual por el pasado por parametros retornando el valor viejo
	 * @param e Arco
	 * @param x Nuevo rotulo
	 * @return Rotulo viejo
	 * @throws InvalidEdgeException Si el arco es invalido
	 */
	public E replace( Edge<E> e, E x) throws InvalidEdgeException;
	
	/**
	 * Crea un nuevo vertice con el rotulo pasado por parametros
	 * @param x Rotulo del vertice
	 * @return Nuevo vertice
	 */
	public Vertex insertVertex(int x);
	
	/**
	 * Dados dos vertices y un rotulo, crea un nuevo arco entre los dos vertices con el rotulo pasado
	 * por parametros y lo retorna
	 * @param v Vertice origen
	 * @param w Vertice destino
	 * @param x Rotulo
	 * @return Nuevo arco
	 * @throws InvalidVertexException Lanza excepcion si algun vertice es invalido
	 */
	public Edge<E> insertEdge(Vertex v, Vertex w, E x) throws InvalidVertexException;
	/**
	 * Dados dos vertices y un rotulo, crea un nuevo arco entre los dos vertices con el rotulo pasado
	 * por parametros y lo retorna
	 * @param v Vertice origen
	 * @param w Vertice destino
	 * @param x Rotulo
	 * @return Nuevo arco
	 * @throws InvalidVertexException Lanza excepcion si algun vertice es invalido
	 */
	public Edge<E> insertDirectedEdge(Vertex v, Vertex w, E x) throws InvalidVertexException;
	
	/**
	 * Elimina el vertice pasado por parametros y retorna su rotulo
	 * @param v Vertice a eliminar
	 * @return Rotulo del vertice
	 * @throws InvalidVertexException Lanza excepcion si el vertice es invalido
	 */
	public int removeVertex(Vertex v) throws InvalidVertexException;
	
	/**
	 * Elimina el arco pasado por parametros y retorna su rotulo
	 * @param e Arco a eliminar
	 * @return Rotulo del Arco
	 * @throws InvalidEdgeException Lanza excepcion si el arco pasado por parametros es invalido
	 */
	public E removeEdge(Edge<E> e) throws InvalidEdgeException;

}
