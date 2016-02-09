package TDALista;
/**
 * 
 * @author COMISION 2: Franco Raniolo, Jose Ochoa
 *	
 *	Excepcion lanzada al salir del rango de la lista.
 */
public class BoundaryViolationException extends Exception {
	public BoundaryViolationException (String s){
		super(s);
	}

}
