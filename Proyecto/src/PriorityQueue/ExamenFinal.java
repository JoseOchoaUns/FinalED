package PriorityQueue;

import java.util.Date;

public class ExamenFinal
{
	private String nombreMateria;
	private Integer nota;
	private Date fecha;
	
	public ExamenFinal (String nm, int nota, Date fe){
			nombreMateria = nm;
			this.nota= nota;
			fecha = fe;
	
		}
	
	public void setNM (String s){
			nombreMateria = s;
	}
	public String getNM(){
		return nombreMateria;
	}
	
	public void setNota(int n){
		nota = n;
	}
	public Date getFecha(){
		return fecha;
	}
	public void setFecha(Date f){
		fecha=f;
	}

	public int getNota() {
		return nota;
	}
}


