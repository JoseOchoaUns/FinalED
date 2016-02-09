package TDAMapeo;
/**
 * 
 * @author COMISION 2 : Ochoa Jose y Franco Raniolo
 *
 */
public class Entrada<K,V>  implements Entry<K,V>{
	/*
	 * Key
	 */
	protected K key;
	/*
	 * Value
	 */
	protected V value;
	/**
	 * Constructor de la entrada
	 * @param p Key
	 * @param e Value
	 */
	public Entrada(K p, V e){
		key=p;
		value=e;
	}
	
	public K getKey(){
		return key;
	}

	
	public V getValue() {
		return value;
	}
	/**
	 * Setea el valor del value
	 * @param v Nuevo valor del value
	 */
	public void setValue(V v){
		value = v;
	}
}
	