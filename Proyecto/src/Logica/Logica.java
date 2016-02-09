package Logica;
import TDALista.*;
import TDAMapeo.*;
import TDAQueue.*;
import TDAGraph.*;
/**
 *
 * @author Comision 2 : Franco Raniolo y Jose Ochoa
 * * 
 */

public class Logica<E> {
	
	private Object ESTADO;
	private Object VISITADO;
	private Object NOVISITADO;
	private Graph<E> grafo;
	private PositionList<Vertex> vertices;
	
	
	/**
	 * Constructor la clase Logica
	 */
	public Logica (){
	}
	
	/**
	 * Metodo que dado un rotulo, retorna el vertex del grafo con el mismo rotulo 
	 * @param i Rotulo
	 * @return Vertex con rotulo i, O null si no encuentra un vertex con dicho rotulo
	 */
	public Vertex recuperarVertex(int i){
		for(Vertex v : vertices)
			if(v.element() == i)
				return v;
		return null; //Nunca va a pasar por como esta implementada la interfaz
	}
	
	
	/**
	 * Inicializa el grafo y la lista de vertex
	 */
	public void crearGrafo(){
		grafo = new DiGrafo<E>();
		vertices = new ListaDoblementeEnlazada<Vertex>();
	}
	
	
	/**
	 * Agrega un vertice con rotulo x al grafo
	 * @param x Rotulo del nuevo vertice
	 * @return Valor del rotulo agregado
	 */
	public int agregarVertice(int x){
		Vertex v = grafo.insertVertex(x);
		vertices.addLast(v);
		return x;
	}
	
	
	/**
	 * Agrega un arco de rotulo x entre los vertices v1 y v2
	 * @param v1 Vertice de origen
	 * @param v2 Vertice de llegada
	 */
	public void agregarArco(Vertex v1, Vertex v2){
		try{
			grafo.insertEdge(v1, v2, null); //No es recomendable pasar un nulo.
		}catch(InvalidVertexException e){
			System.out.println("Vertex invalido");
		}
	}
	
	
	/**
	 * Elimina del grafo el vertice pasado por parametro
	 * @param v1 Vertice a eliminar
	 */
	public void eliminarVertice(Vertex v1){
		try{
			Position<Vertex> remover = null;
			for(Position<Vertex> pos : vertices.positions())
				if(pos.element() == v1){
					remover = pos;
				}
			vertices.remove(remover);
			grafo.removeVertex(v1);
		}catch(InvalidVertexException e){
			System.out.println("Vertex invalido");
		}catch(InvalidPositionException e){
			System.out.println("Problema al borrar vertice");
		}
	}
	
	
	/**
	 * 
	 * Muestra todos los rotulos del grafo en un recorrido DFS
	 *
	 * @return String del recorrido DFS del grafo
	 */
	public String DFSShell(){
		Queue<String> s = new ColaEnlazada<String>();
		try{
			ESTADO = new Object();
			NOVISITADO = new Object();
			VISITADO = new Object();
			
			for(Vertex v : grafo.vertices()){
				v.put(ESTADO, NOVISITADO);
				
			}
			for(Vertex v : grafo.vertices())
				if(v.get(ESTADO) == NOVISITADO){
					DFS(v, s);
				}
			
		}catch(InvalidKeyException e){
			System.out.println("Key invalida");
		}
		String n = "";
		while(!s.isEmpty()){ //Agregamos todo a una cola, si pasabamos la String por parametro no funcionaba
			try {
				n+= s.dequeue() + " ";
			} catch (EmptyQueueException e) {
				System.out.println("Error al extraer elementos de la cola"); //nunca va a pasar
			}
		}
		return "El recorrido DFS del grafo es: " + n;
	}
	private void DFS(Vertex v,Queue<String> s) {
		try{
			s.enqueue(Integer.toString(v.element()));
			v.put(ESTADO,VISITADO);
			for(Edge<E> e : grafo.emergentEdges(v))
				if(grafo.opposite(v,e).get(ESTADO) == NOVISITADO)
					DFS(grafo.opposite(v,e),s);
			
		}catch(InvalidKeyException e){
			System.out.println("Key invalida");
		}catch(InvalidVertexException e){
			System.out.println("Vertex invalido");
		}catch(InvalidEdgeException e){
			System.out.println("Edge invalido");
		}
	}
	/**
	 * Muestra todos los rotulos del grafo en un recorrido BFS
	 * @return String del recorrido BFS
	 */
	public String BFSShell(){
		Queue<String> s = new ColaEnlazada<String>();
		try{
			ESTADO = new Object();
			NOVISITADO = new Object();
			VISITADO = new Object();
			for(Vertex v : grafo.vertices())
				v.put(ESTADO, NOVISITADO);
			for(Vertex v : grafo.vertices())
				if(v.get(ESTADO) == NOVISITADO)
					BFS(v, s);
			
		}catch(InvalidKeyException e){
			System.out.println("Key invalida");
		}
		String n = "";
		while(!s.isEmpty()){
			try {
				n += s.dequeue() + " ";
			} catch (EmptyQueueException e) {
				System.out.println("Error al extraer elementos de la cola"); //nunca va a pasar
			}
		}
		
		return "El recorrido BFS del grafo es: " +n;
	}
	private void BFS(Vertex v,Queue<String> s) {
		try{
			v.put(ESTADO, VISITADO);
			Queue<Vertex> cola = new ColaEnlazada<Vertex>();
			cola.enqueue(v);
			s.enqueue(Integer.toString(v.element()));
			while(!cola.isEmpty()){
				Vertex w = cola.dequeue();
				for(Edge<E> e : grafo.emergentEdges(w)){
					if(grafo.opposite(w, e).get(ESTADO)== NOVISITADO){
						cola.enqueue(grafo.opposite(w, e));
						s.enqueue(Integer.toString(grafo.opposite(w, e).element()));
						grafo.opposite(w, e).put(ESTADO,VISITADO);
					}
				}
			}
		}catch(InvalidKeyException e){
			System.out.println("Key invalida");
		}catch(InvalidVertexException e){
			System.out.println("Vertex invalido");
		}catch(InvalidEdgeException e){
			System.out.println("Edge invalido");
		}catch(EmptyQueueException e){
			System.out.println("Cola vacia");
		}
	}
	/**
	 * Metodo que retorna un string con todos los caminos entre un vertice de rotulo A y otro con rotulo B
	 * Si esos vertices no existen retorna una string con un mensaje de que no existe
	 * Si no hay camino retorna una string indicando que no hay camino
	 * @param A rotulo del vertice inicial
	 * @param B	rotulo del vertice final
	 * @return String con los rotulos del camino minimo entre A y B
	 */
	public String HallarTodosCaminos(int A, int B){
		String s = "";
		ESTADO = new Object();
		VISITADO = new Object();
		NOVISITADO = new Object();
		PositionList<Vertex> camino = new ListaDoblementeEnlazada<Vertex>();
		PositionList<PositionList<Vertex>> listaCaminos = new ListaDoblementeEnlazada<PositionList<Vertex>>();
		Vertex v1 = null;
		Vertex v2 = null;
		for(Vertex v : grafo.vertices()){ // busco el vertice con rotulo A y el vertice con rotulo B
			if(v1!= null && v2 != null)
				break;
			if(v.element() == A)
				v1 = v;
			if(v.element() == B)
				v2 = v;
		}
		if(v1 == null || v2 == null)
			return "Vertice/s fuera del grafo"; 
		else{
			try{
				for(Vertex v : grafo.vertices()){
					v.put(ESTADO, NOVISITADO);
				}
			}catch(InvalidKeyException e){
				System.out.println("Key invalida");
			}
			
			HallarTodosCaminosAux(v1,v2,camino,listaCaminos);
			if(listaCaminos.isEmpty())
				return "No existe un camino entre los vertices dados";
			else{
				if(listaCaminos.size()== 1){
					s+="El unico camino entre los vertices dados es :";					
				for(Position<PositionList<Vertex>> pos : listaCaminos.positions())
					for(Position<Vertex> pos2 : pos.element().positions()){
						s +=pos2.element().element() + " ";
					}
				return s;
				}
				else{
					s+="Los caminos entre los vertices dados son :";					
					for(Position<PositionList<Vertex>> pos : listaCaminos.positions()){
						for(Position<Vertex> pos2 : pos.element().positions()){
							s +=pos2.element().element() + " ";
						}
						s+="    #    ";
					}
					return s;
				}
			}
		}
	}
	private void HallarTodosCaminosAux(Vertex v1, Vertex v2,PositionList<Vertex> camino,PositionList<PositionList<Vertex>> caminos){
		try{
			v1.put(ESTADO, VISITADO);
			camino.addLast(v1);
			if(v1 == v2){
				caminos.addLast(new ListaDoblementeEnlazada<Vertex>());
				for(Position<Vertex> v : camino.positions()){
					caminos.last().element().addLast((v.element()));
				}
							
			}
			else{
				for(Edge<E> e: grafo.emergentEdges(v1)){
					if(grafo.opposite(v1, e).get(ESTADO)== NOVISITADO){
						HallarTodosCaminosAux(grafo.opposite(v1, e),v2,camino,caminos);
					}
				}
				
			}
			camino.remove(camino.last());
			v1.put(ESTADO, NOVISITADO);
			}catch(InvalidKeyException e){
				System.out.println("Key invalida");
			}catch(InvalidVertexException e){
				System.out.println("Vertex invalido");
			}catch(InvalidEdgeException e){
				System.out.println("Edge invalido");
			}catch(InvalidPositionException e){
				System.out.println("Posicion invalida");
			}catch(EmptyListException e){
				System.out.println("Lista vacia");
			}
							
		}
	
	
	/**
	 * Metodo que retorna una string con los rotulos del camino mas corto entre un vertice de rotulo A y otro con rotulo B
	 * Si esos vertices no existen retorna una string con un mensaje de que no existe
	 * Si no hay camino retorna una string indicando que no hay camino
	 * @param A rotulo del vertice inicial
	 * @param B	rotulo del vertice final
	 * @return String con los rotulos del camino minimo
	 */
	public String HallarCaminoMasCorto(int A, int B){
		String s = "";
		ESTADO = new Object();
		VISITADO = new Object();
		NOVISITADO = new Object();
		PositionList<Vertex> caminoActual = new ListaDoblementeEnlazada<Vertex>();
		PositionList<Vertex> caminoCorto = new ListaDoblementeEnlazada<Vertex>();
		Vertex v1 = null;
		Vertex v2 = null;
		for(Vertex v : grafo.vertices()){ // busco el vertice con rotulo A y el vertice con rotulo B
			if(v1!= null && v2 != null)
				break;
			if(v.element() == A)
				v1 = v;
			if(v.element() == B)
				v2 = v;
		}
		if(v1 == null || v2 == null)
			return "Vertice/s fuera del grafo";
		else{	
			try{
				for(Vertex v : grafo.vertices())
					v.put(ESTADO,NOVISITADO);
				HallarCaminoMasCortoAux(v1,v2,caminoActual,caminoCorto);
	
			}catch(InvalidKeyException e){
				System.out.println("Key invalida");
			}
			
			if(caminoCorto.isEmpty())
				return "No existe un camino entre los vertices dados";
			else{
				s+="El camino mas corto entre los vertices dados es :";
				
				for(Position<Vertex> pos : caminoCorto.positions()){
					s+= pos.element().element() + " ";
				}
			}
			return s;
		}
	}
	
	
	/**
	 * Metodo privado auxiliar para poder hallar el camino mas corto
	 * @param v1 Vertice recorrido 
	 * @param v2 Vertice de llegada
	 * @param actual Camino actual
	 * @param corto Camino mas corto guardado
	 */
	public void HallarCaminoMasCortoAux(Vertex v1, Vertex v2,PositionList<Vertex> actual, PositionList<Vertex> corto){
		try{			
			v1.put(ESTADO, VISITADO);
			actual.addLast(v1);
			if(v1 == v2){
				if(corto.isEmpty()){
					for(Position<Vertex> pos : actual.positions())
						corto.addLast(pos.element());
				}
				if(corto.size() > actual.size()){
					corto = new ListaDoblementeEnlazada<Vertex>();
					for(Position<Vertex> pos : actual.positions())
						corto.addLast(pos.element());
				}
			}
			else{
				for(Edge<E> e : grafo.emergentEdges(v1)){
					if(grafo.opposite(v1,e).get(ESTADO)==NOVISITADO){
						HallarCaminoMasCortoAux(grafo.opposite(v1,e),v2,actual,corto);
					}
				}
			}
			actual.remove(actual.last());
			v1.put(ESTADO,NOVISITADO);
		}catch(InvalidKeyException e){
			System.out.println("Key invalida");
		}catch(InvalidEdgeException e){
			System.out.println("Edge invalido");
		}catch(InvalidVertexException e){
			System.out.println("Vertex invalido");
		}catch(EmptyListException e){
			System.out.println("Lista vacia");
		}catch(InvalidPositionException e){
			System.out.println("Posicion invalida");
		}
		
	}
	
	
	/**
	 * Elimina el camino mas corto entre un vertice de rotulo A y un vertice de rotulo B, sin eliminar el vertice de origen ni el final
	 * Si esos vertices no existen muestra un mensaje de que no existe
	 * Si elimino aunquesea un vertice mostrara un mensaje diciendo que se han borrado satisfactoriamente  
	 * @param A rotulo del vertice inicial
	 * @param B rotulo del vertice final
	 * @return Una cola de rotulos que servira para poder borrar los vertices de los ComboBox de la clase GUI
	 */
	public Queue<Integer> EliminarCaminoMinimo(int A, int B){
		Queue<Integer> cola = new ColaEnlazada<Integer>();
		ESTADO = new Object();
		VISITADO = new Object();
		NOVISITADO = new Object();
		PositionList<Vertex> caminoActual = new ListaDoblementeEnlazada<Vertex>();
		PositionList<Vertex> caminoCorto = new ListaDoblementeEnlazada<Vertex>();
		Vertex v1 = null;
		Vertex v2 = null;
		for(Vertex v : grafo.vertices()){ // busco el vertice con rotulo A y el vertice con rotulo B
			if(v1!= null && v2 != null)
				break;
			if(v.element() == A)
				v1 = v;
			if(v.element() == B)
				v2 = v;
		}
		if(v1 == null || v2 == null)
			return cola;
		else{	
			try{
				for(Vertex v : grafo.vertices())
					v.put(ESTADO,NOVISITADO);
				HallarCaminoMasCortoAux(v1,v2,caminoActual,caminoCorto);
				
				for(Position<Vertex> pos : caminoCorto.positions()){
					if(caminoCorto.first() != pos && caminoCorto.last() != pos){  // Verifica que no sea ni el primer ni ultimo vertice
						vertices.remove(pos);
						grafo.removeVertex(pos.element());
						cola.enqueue(pos.element().element());
					}
				}
				if(cola.size() == 0){ //CASO EXCEPCIONAL EN EL UNICO CAMINO SEAN LOS VERTICES
					for(Edge<E> e : grafo.emergentEdges(v1)){ //BORRO EL ARCO QUE UNE A LOS VERTICES
						if(grafo.opposite(v1, e) ==  v2){
							grafo.removeEdge(e);
							break;
						}
					}
				}
				
			}catch(InvalidKeyException e){
				System.out.println("Key invalida");
			}catch(EmptyListException e){
				System.out.println("Lista vacia");
			}catch(InvalidVertexException e){
				System.out.println("Vertex invalido");
			}catch(InvalidPositionException e){
				System.out.println("Posicion invalida");
			}catch(InvalidEdgeException e){
				System.out.println("Edge invalido");
			}
			return cola;
		}
	}		
}
