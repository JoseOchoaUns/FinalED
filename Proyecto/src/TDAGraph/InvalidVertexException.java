package TDAGraph;

/**
 * 
 * @author Comision 2 : Jose Ochoa, Franco Raniolo
 *
 *	Excepcion lanzada al recibir un Vertex invalido.
 */

public class InvalidVertexException extends Exception {
	public InvalidVertexException (String s){
		super(s);
	}
}
