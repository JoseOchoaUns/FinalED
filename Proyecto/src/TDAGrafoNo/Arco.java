package TDAGrafoNo;

import TDALista.Position;

public class Arco<V,E> implements Edge<E> {
	
	private E rotulo;
	private Position<Arco<V,E>> posLA,posV1,posV2;
	private Vertice<V,E> v1,v2;
	
	public Arco (Vertice<V,E> v1, Vertice<V,E> v2, E e){
		this.v1=v1;
		this.v2=v2;
		rotulo = e;
	}
	
	public E element(){
		return rotulo;
	}
	
	public void setElement(E e){
		rotulo = e;
	}
	public Position<Arco<V,E>> getPosV1(){
		return posV1;
	}
	public void setPosV1(Position<Arco<V,E>> pos){
		posV1 = pos;
	}
	
	public Position<Arco<V,E>> getPosV2(){
		return posV2;
	}
	
	public void setPosV2(Position<Arco<V,E>> pos){
		posV2= pos;
	}
	
	public Position<Arco<V,E>> getPosLA(){
		return posLA;
	}
	
	public void setPosLA(Position<Arco<V,E>> pos){
		posLA = pos;
	}
	
	public Vertice<V,E> getV1(){
		return v1;
	}
	public Vertice<V,E> getV2(){
		return v2;
	}
}
	
