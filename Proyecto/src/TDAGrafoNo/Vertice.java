package TDAGrafoNo;

import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class Vertice<V,E> implements Vertex<V> {

	private V rot;
	private PositionList<Arco<V,E>> ady;
	private Position <Vertice<V,E>> posLV;
	
	public Vertice(V v){
		rot = v;
		ady = new ListaDoblementeEnlazada<Arco<V,E>>();
	}
	
	public V element(){
		return rot;
	}
	public void setElement(V v){
		rot = v;
	}
	
	public PositionList<Arco<V,E>> getAdyacentes(){
		return ady;
	}
	
	public Position<Vertice<V,E>> getPosLV(){
		return posLV;
	}
	
	public void setPosLV(Position<Vertice<V,E>> pos){
		posLV = pos;
	}
	
	
}
