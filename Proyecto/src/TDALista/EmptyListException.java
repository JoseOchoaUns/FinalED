package TDALista;
/**
 * 
 * @author Comision 2: Franco Raniolo y Jose Ochoa
 * 
 * Excepcion lanzada si se llama a last() o first() y la lista esta vacia
 */
public class EmptyListException extends Exception {
	public EmptyListException(String s){
		super(s);
	}
}
