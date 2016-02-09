package TDAGraph;

import TDAMapeo.MapeoHash;
import TDALista.Position;
import TDALista.PositionList;
import TDALista.ListaDoblementeEnlazada;

/**
 * 
 * @author Comision 2: Franco Raniolo, Jose Ochoa
 *
 * 
 */
public class Vertice <E> extends MapeoHash<Object,Object> implements Vertex {
	
	/*
	 * Rotulo del vertice
	 */
	private int rotulo;
	
	/*
	 * Lista de arcos emergentes
	 */
	private PositionList<Arco<E>> emergentes;
	/*
	 * Lista de arcos incidentes
	 */
	private PositionList<Arco<E>> incidentes;
	/*
	 * Posicion en la lista de vertices de la clase grafo
	 */
	private Position<Vertice<E>> posLV;
	
	/**
	 * Constructor que setea el rotulo con el valor pasador por parametros e incializa la lista de arcos
	 * emergentes e incidentes
	 * @param rotulo Rotulo del nuevo vertice
	 */
	public Vertice( int rotulo ) {
		
		this.rotulo = rotulo;
		emergentes = new ListaDoblementeEnlazada<Arco<E>>();
		incidentes = new ListaDoblementeEnlazada<Arco<E>>();
	}
	
	/**
	 * Setea el valor del rotulo
	 * @param v Valor nuevo del rotulo
	 */
	public void setElement(int v){
		rotulo = v;
	}
	
	public Integer element(){
		return rotulo; 
	}
	
	/**
	 * Retorna la lista de arcos emergentes
	 * @return Lista de arcos emergentes del vertice
	 */
	public PositionList<Arco<E>> getEmergentes(){
		return emergentes;
	}
	
	/**
	 * Retorna la lista de arcos incidentes
	 * @return Lista de arcos incidentes del vertice
	 */
	public PositionList<Arco<E>> getIncidentes(){
		return incidentes;
	}
	/**
	 * Retorna la posicion en la lista de vertices
	 * @return Posicion en la lista de vertices
	 */
	public Position<Vertice<E>> getPosLV(){
		return posLV;
	}
	/**
	 * Setea la posicion en la lista de vertices
	 * @param p Posicion en la lista de vertices
	 */
	public void setPosLV(Position <Vertice<E>> p){
		posLV = p;
	}
}