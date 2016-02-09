package PriorityQueue;

public class Main {

	
	public static <ExamenFinal,Alumno> void mejoresAlumnos(PriorityQueue<ExamenFinal, Alumno> PQ, int k){
		PriorityQueue<ExamenFinal, Alumno> colaAux = new Heap(PQ.size(), new DefaultComparator());
		while(!PQ.isEmpty()){
			Entry<ExamenFinal,Alumno> ent= PQ.removeMin();
			colaAux.insert(ent.getKey(),ent.getValue());			
		}
		while(!colaAux.isEmpty()){
			Entry<ExamenFinal,Alumno> ent1 = colaAux.removeMin();
			PQ.insert(ent1.getKey(), ent1.getValue());
		}
		while(k > 0 && !PQ.isEmpty()){
			System.out.println(PQ.removeMin().getValue().getNombre());
			k--;
		}
	}
	

}