package TDALista;
/**
 * 
 * @author Comision 2: Franco Raniolo y Jose Ochoa
 *
 *	Excepcion lanzada por el iterador si se pide un next y no lo hay.
 */
public class NoSuchElementException extends RuntimeException {
	public NoSuchElementException (String s){
		super(s);
	}
}
