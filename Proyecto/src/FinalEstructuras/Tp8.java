 package FinalEstructuras;

import TDAGraph.*;
import TDALista.*;
import TDAMapeo.*;
import TDAQueue.*;




public class Tp8 {
	
	private static Object ESTADO;
	private static Object VISITADO;
	private static Object NOVISITADO;
	
	public static <E> boolean esConexo(Graph<E> g){
		ESTADO = new Object();
		VISITADO = new Object();
		NOVISITADO = new Object();
		int contador = 0;
		try{
			for(Vertex v: g.vertices())
				v.put(ESTADO, NOVISITADO);
			
			for(Vertex v: g.vertices())
				if(v.get(ESTADO)==NOVISITADO){
					bfs(g,v);
					contador++;
				}
		}catch(InvalidKeyException e){
			
		}
		return contador > 1 ? false: true;
	}
	
	
	
	
	
	
	public static <E> void BFSshell(Graph<E> g)
	{
		ESTADO = new Object();
		VISITADO = new Object();
		NOVISITADO = new Object();
		
		try{
		
		for(Vertex v : g.vertices())
		{
			v.put(ESTADO, NOVISITADO);
		}
		for(Vertex v : g.vertices())
		{
			if(v.get(ESTADO) == NOVISITADO){
				bfs(g,v);
			}
		}
		
		}
		catch (InvalidKeyException e){
		
		}
	}
	
	private static <E> void bfs (Graph<E> g, Vertex v){
		
		try{
			Queue<Vertex> cola = new ColaEnlazada<Vertex>();
			cola.enqueue(v);
			while(!cola.isEmpty()){
				Vertex w = cola.dequeue();
				System.out.println(w.element());
				w.put(ESTADO, VISITADO);
				for(Edge<E> x : g.emergentEdges(w))
				{
					if (g.opposite(w, x).get(ESTADO) == NOVISITADO){
						cola.enqueue(g.opposite(w, x));
					}
				}
			}
		}catch(EmptyQueueException e){
			
		}catch(InvalidVertexException e){
			
		}catch(InvalidEdgeException e){
			
		}catch(InvalidKeyException e){
			
		}
	}
	
	
	public static <E> void DFSshell(Graph<E> g){
		
		ESTADO = new Object();
		VISITADO = new Object();
		NOVISITADO = new Object();
		
		try{
		
			for(Vertex v : g.vertices()){
				v.put(ESTADO, NOVISITADO);
			}
			for(Vertex v : g.vertices()){
				if(v.get(ESTADO) == NOVISITADO){
					dfs(g,v);
				}
			}
		}
		catch (InvalidKeyException e){
			
		}
	}
	private static <E> void dfs(Graph<E> g, Vertex v ){
		System.out.println(v.element());
		
		try{
			v.put(ESTADO, VISITADO);
			for(Edge<E> e : g.emergentEdges(v)){
				Vertex opuesto = g.opposite(v, e);
				if(opuesto.get(ESTADO)== NOVISITADO){
					dfs(g,opuesto);
				}
			}
		}catch(InvalidKeyException e){
			
		}catch(InvalidVertexException e){
			
		}catch(InvalidEdgeException e){
			
		}
		
	}
	
	public static <E> PositionList<Vertex> HallarCaminoCorto(Graph<E> g, Vertex v1,Vertex v2){
		ESTADO = new Object();
		VISITADO = new Object();
		NOVISITADO = new Object();
		PositionList<Vertex> caminoCorto = new ListaDoblementeEnlazada<Vertex>();
		PositionList<Vertex> caminoActual = new ListaDoblementeEnlazada<Vertex>();
		try{
			for(Vertex v : g.vertices())
				v.put(ESTADO, NOVISITADO);
			
		}catch (InvalidKeyException e){
			
		}
		HallarCaminoCortoAux(g,v1,v2,caminoCorto,caminoActual);
		return caminoCorto;
	}
	
	private static <E> void HallarCaminoCortoAux(Graph<E> g, Vertex v1,Vertex v2,PositionList<Vertex> corto,
			PositionList<Vertex> actual){
		
		try{
			v1.put(ESTADO, VISITADO);
			actual.addLast(v1);
			if(v1 == v2){
				if(corto.isEmpty())
					for(Vertex v : actual)
						corto.addLast(v);
				else
					if(corto.size() > corto.size()){
						corto = new ListaDoblementeEnlazada<Vertex>();
						for(Vertex v: actual)
							corto.addLast(v);
					}
						
			}
			else{
				for(Edge<E> e: g.emergentEdges(v1)){
					Vertex opuesto = g.opposite(v1, e);
					if(opuesto.get(ESTADO) == NOVISITADO){
						HallarCaminoCortoAux(g,opuesto,v2,corto,actual);
					}
				} 
			}
			v1.put(ESTADO, NOVISITADO);
			actual.remove(actual.last());
		}catch(InvalidKeyException e){
			
		}catch(InvalidVertexException e){
			
		}catch(InvalidEdgeException e){
			
		}catch(InvalidPositionException e){
			
		}catch(EmptyListException e){
			
		}
	}
	
	public static <E> boolean HallarCiclo (Graph<E> g, Vertex v1){
		
		ESTADO = new Object();
		VISITADO = new Object();
		NOVISITADO = new Object();
		boolean devolver = true;
		
		try{ 
			
		for(Vertex v : g.vertices())
			v.put(ESTADO, NOVISITADO);
		
		for(Edge<E> e : g.emergentEdges(v1)){
			Vertex opuesto = g.opposite(v1, e);
			if(opuesto.get(ESTADO)==NOVISITADO)
				devolver = HallarCicloAux(g,opuesto,v1);
		}
		}catch(InvalidKeyException e){
			
		}catch(InvalidVertexException e){
			
		}catch(InvalidEdgeException e){
			
		}
		
		
		return devolver;
	}
	
	private static <E> boolean HallarCicloAux(Graph <E> g, Vertex origen, Vertex v1){
		
		Queue<Vertex> cola = new ColaEnlazada<Vertex>();
		cola.enqueue(origen);
		
		try{
			while(!cola.isEmpty()){
				Vertex w = cola.dequeue();
				w.put(ESTADO, VISITADO);
				for(Edge<E> e : g.emergentEdges(w)){
					Vertex opuesto = g.opposite(w, e);
					if(opuesto == v1)
						return true;
					else
						if(opuesto.get(ESTADO)==NOVISITADO)
							cola.enqueue(opuesto);
				}
			}
		}catch(InvalidKeyException e){
			
		}catch(EmptyQueueException e){
			
		}catch(InvalidVertexException e){
			
		}catch(InvalidEdgeException e){
			
		}
		
		return false;
	}
	
	
	public static <E> PositionList<Vertex> HallarCamino (Graph<E> g,Vertex v1,Vertex v2){
		PositionList<Vertex> camino = new ListaDoblementeEnlazada<Vertex>();
		ESTADO = new Object();
		VISITADO = new Object();
		NOVISITADO = new Object();
		
		for(Vertex v: g.vertices())
			try {
				v.put(ESTADO, NOVISITADO);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		HallarCaminoAux(g,v1,v2,camino);
		return camino;
	}
	
	public static <E> boolean HallarCaminoAux(Graph<E> g, Vertex origen, Vertex destino, PositionList<Vertex> camino){
		camino.addLast(origen);
		try {
			origen.put(ESTADO,VISITADO);
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(origen == destino){
			return true;
		}
		else{
			try {
				for(Edge<E> e: g.emergentEdges(origen)){
					Vertex opuesto = g.opposite(origen,e);
					if(opuesto.get(ESTADO)==NOVISITADO){
						boolean encontre = HallarCaminoAux(g,opuesto,destino,camino);
						if(encontre)
							return true;
					}
				}
			} catch (InvalidVertexException | InvalidEdgeException
					| InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				camino.remove(camino.last());
			} catch (InvalidPositionException | EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;	
		}
	}
	
	public static void main (String[] arg){
		DiGrafo<Integer> g = new DiGrafo<Integer>();
		Vertex v1 = g.insertVertex(1);
		Vertex v2 = g.insertVertex(2);
		Vertex v3 = g.insertVertex(3);
		Vertex v4 = g.insertVertex(4);
		Vertex v5 = g.insertVertex(5);
		Vertex v6 = g.insertVertex(6);
		try{
			g.insertDirectedEdge(v1, v4, 1);
			g.insertDirectedEdge(v4, v3, 1);
			g.insertDirectedEdge(v3, v3, 1);
			g.insertDirectedEdge(v3, v2, 1);
			g.insertDirectedEdge(v2, v4, 1);
			g.insertDirectedEdge(v4, v5, 1);
			g.insertDirectedEdge(v5, v1, 1);
			g.insertDirectedEdge(v6, v6, 1);
		}catch(InvalidVertexException e){
			
		}
		
		
		
		System.out.println(esConexo(g));
		//System.out.println(HallarCiclo(g,v1));
		System.out.println("BFS");
		g.BFSshell();
		System.out.println("-");
		System.out.println("-");
		System.out.println("-");
		System.out.println("DFS");
		g.DFSshell();
		System.out.println("-");
		System.out.println("-");
		System.out.println("-");
		
	}
}

