package TDALista;
/**
 * 
 * @author Comision 2: Franco Raniolo y Jose Ochoa
 *
 * Excepcion lanzada al recibir una posicion invalida.
 */
public class InvalidPositionException extends Exception {
	public InvalidPositionException (String s){
		super(s);
	}
}
