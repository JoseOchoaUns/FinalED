package TDAMapeo;
/**
 * 
 * @author COMISION 2 : Franco Raniolo y Jose Ochoa
 *
 * @param <K> Tipo de la Key
 * @param <V> Tipo del valor
 */
public interface Entry<K,V> {
	/**
	 * Metodo que retorna el valor de la key de la entry
	 * @return valor de la key de la entry
	 */
	public K getKey();
	/**
	 * Metodo que retorna el valor de la value de la entry
	 * @return valor de la value de la entry
	 */
	public V getValue(); 
	
}
