package TDALista;

import java.util.Iterator;
/**
 * 
 * @author COMISION 2 : Franco Raniolo y Jose Ochoa
 *
 */
public class ElementIterator<E> implements Iterator<E> {
	protected PositionList <E> list;
	protected Position <E> cursor;
	/**
	 * Constructor del iterador
	 * @param L Lista que sera iterada
	 */
	public ElementIterator(PositionList<E> L){
		list = L;
		try{
		cursor = (list.isEmpty())? null: list.first();
		}
		catch(EmptyListException e){}
		
	}

	
	public boolean hasNext() {
		return (cursor != null);
	}	
	public E next() throws NoSuchElementException {
		E devolver = cursor.element();		
		if (cursor == null)
			throw new NoSuchElementException("No hay siguiente elemento");		
		try{	
			cursor = (cursor == list.last())? null : list.next(cursor);
		} catch (BoundaryViolationException e){			
		} catch (EmptyListException e) {			
		} catch (InvalidPositionException e){
		}	
	 return devolver;		
	}
	public void remove() {			
	}	 
}
	
	 
	
	 

	
	
	

