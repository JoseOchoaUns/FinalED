package TDAMapeo;
/**
 * 
 * @author COMISION 2 : Franco Raniolo y Jose Ochoa
 *
 * Excepcion que es lanzada al recibir una clave invalida.
 */
public class InvalidKeyException extends Exception {
	public InvalidKeyException(String s){
		super(s);
	}
}
