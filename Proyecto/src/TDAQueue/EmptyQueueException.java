package TDAQueue;
/**
 * 
 * @author COMISION 2: Franco Raniolo, Ochoa Jose
 * Excepcion que sucede al intentar sacar un elemento en una cola vacia
 */
public class EmptyQueueException extends Exception {
	public EmptyQueueException(String s){
		super(s);
	}
}
