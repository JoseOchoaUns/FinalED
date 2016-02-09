package PriorityQueue;

public class Entrada<K,V>  implements Entry<K,V>{
	
	protected K prioridad;
	protected V elemento;
	
	public Entrada(K p, V e){
		prioridad=p;
		elemento=e;
	}
	
	public K getKey(){
		return prioridad;
	}

	
	public V getValue() {
		return elemento;
	}
}
	
	