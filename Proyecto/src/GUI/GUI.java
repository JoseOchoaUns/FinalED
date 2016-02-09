package GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Logica.Logica;
import TDAGraph.Vertex;
import TDAQueue.EmptyQueueException;
import TDAQueue.Queue;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Label;
import javax.swing.JComboBox;
import javax.swing.JLabel;
public class GUI <E> {

	private JFrame frame;
	private Logica<E> logica;
	
	//----BOTONES PRINCIPALES---
	private JButton crearGrafo;
	private JButton agregarVertice;
	private JButton agregarArco;
	private JButton eliminar;
	private JButton mostrarBFS;
	private JButton mostrarDFS;
	private JButton mostrarCorto;
	private JButton todosRecorridos;
	private JButton eliminarCortos;
	//----BOTONES OK-------
	private JButton Ok;
	private JButton Ok2;
	private JButton Ok3;
	private JButton Ok4;
	private JButton Ok5;
	//----BOTONES CANCELAR
	private JButton Cancel;
	private JButton Cancel2 ;
	//----BOTON PARA BORRAR VERTICES
	private JButton borrar;
	//----COMBOBOX QUE TENDRAN LOS ROTULOS DE LOS VERTICES
	private JComboBox<Integer> VerticesOrigen ;
	private JComboBox<Integer> VerticesDestino;
	//-----JTEXTPANES PARA INTRODUCIR VALORES
	private JTextPane A;
	private JTextPane B;
	private JTextPane AgregadorVertices;
	//----JTEXTPANE QUE MOSTRARA MENSAJES AL USUARIO
	private JTextPane VentanaMensajes;
	//----LABELS QUE LE INDICARAN AL USUARIO QUE TIENE QUE HACER
	private JLabel insertarVertices;
	private Label label;
	private JLabel lBorrar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI(new Logica());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Inicializa la GUI
	 * @param l Clase logica recibida por parametro
	 */
	public GUI(Logica<E> l) {
		logica = l;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 665, 481);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
//-------------------------------------BOTONES PRINCIPALES -------------------------------------------------------
		
		crearGrafo = new JButton("Crear grafo");
		crearGrafo.addActionListener(new ActionListener() {
			/*
			 * Crea el grafo, habilita el boton para agregar vertices y deshabilita el boton para crear grafo.
			 */
			public void actionPerformed(ActionEvent arg0) {
				logica.crearGrafo();
				agregarVertice.setEnabled(true);
				crearGrafo.setEnabled(false);
			}
		});
		crearGrafo .setBounds(21, 0, 200, 23);
		frame.getContentPane().add(crearGrafo);
		
		agregarVertice = new JButton("Agregar Vertice");
		agregarVertice.addActionListener(new ActionListener() {
			/*
			 * Bloquea todos los botones principales y hace visible lo necesario para poder agregar un vertice
			 */
			public void actionPerformed(ActionEvent e) {
				bloquearBotones();
				label.setVisible(true);
				Ok.setVisible(true);
				Cancel.setVisible(true);
				AgregadorVertices.setVisible(true);				
			}
		});
		agregarVertice.setEnabled(false);
		agregarVertice.setBounds(21, 34, 200, 23);
		frame.getContentPane().add(agregarVertice);
		
		
		agregarArco = new JButton("Agregar arco");
		agregarArco.setEnabled(false);
		agregarArco.addActionListener(new ActionListener() {
			/*
			 * Bloquea todos los botones principales y hace visible lo necesario para poder agregar un arco al grafo
			 */
			public void actionPerformed(ActionEvent e) {
				bloquearBotones();
				Cancel.setVisible(true);
				VerticesOrigen.setVisible(true);
				VerticesDestino.setVisible(true);
				Ok2.setVisible(true);
			}
		});
		agregarArco.setBounds(21, 68, 200, 23);
		frame.getContentPane().add(agregarArco);
		
		eliminar = new JButton("Eliminar vertice");
		eliminar.setEnabled(false);
		eliminar.addActionListener(new ActionListener() {
			/*
			 * Bloquea todos los botones principales y hace visible lo necesario para poder eliminar un vertice del grafo
			 */
			public void actionPerformed(ActionEvent arg0) {
				bloquearBotones();
				lBorrar.setVisible(true);
				VerticesOrigen.setVisible(true);
				borrar.setVisible(true);
				Cancel.setVisible(true);
			}
		});
		eliminar.setBounds(21, 94, 200, 23);
		frame.getContentPane().add(eliminar);
		
		mostrarDFS = new JButton("Mostrar vertices DFS");
		mostrarDFS.addActionListener(new ActionListener() {
			/*
			 * Muestra en la ventana de mensajes el recorrido DFS del grafo
			 */
			public void actionPerformed(ActionEvent e) {
				VentanaMensajes.setText(logica.DFSShell());
			}
		});
		mostrarDFS.setEnabled(false);
		mostrarDFS.setBounds(21, 122, 200, 43);
		frame.getContentPane().add(mostrarDFS);
		
		mostrarBFS = new JButton("Mostrar vertices BFS");
		mostrarBFS.setEnabled(false);
		mostrarBFS.addActionListener(new ActionListener() {
			/*
			 * Muestra en la ventana de mensajes el recorrido BFS del grafo
			 */
			public void actionPerformed(ActionEvent e) {
				VentanaMensajes.setText(logica.BFSShell());
			}
		});
		mostrarBFS.setBounds(21, 173, 200, 43);
		frame.getContentPane().add(mostrarBFS);
		
		mostrarCorto = new JButton("Recorrido mas corto");
		mostrarCorto.setEnabled(false);
		mostrarCorto.addActionListener(new ActionListener() {
			/*
			 * Bloquea todos los botones principales y hace visible lo necesario para poder encontrar el camino mas corto entre 2 vertices
			 */
			public void actionPerformed(ActionEvent e) {
				bloquearBotones();
				Ok3.setVisible(true);	
				Cancel2.setVisible(true);
				insertarVertices.setVisible(true);
				A.setVisible(true);
				B.setVisible(true);
				
			}
		});
		mostrarCorto.setBounds(21, 227, 200, 56);
		frame.getContentPane().add(mostrarCorto);
		
		todosRecorridos = new JButton("Todos los recorridos");
		todosRecorridos .setEnabled(false);
		todosRecorridos .addActionListener(new ActionListener() {
			/*
			 * Bloquea todos los botones principales y hace visible lo necesario para poder encontrar todos los caminos entre 2 vertices
			 */
			public void actionPerformed(ActionEvent e) {
				bloquearBotones();
				Ok4.setVisible(true);
				insertarVertices.setVisible(true);
				Cancel2.setVisible(true);
				A.setVisible(true);
				B.setVisible(true);
			}
		});
		todosRecorridos.setBounds(21, 289, 200, 56);
		frame.getContentPane().add(todosRecorridos );
		
		eliminarCortos = new JButton("Eliminar camino mas corto");
		eliminarCortos.addActionListener(new ActionListener() {
			/*
			 * Bloquea todos los botones principales y hace visible lo necesario para poder eliminar el camino mas corto entre 2 vertices (Sin eliminar los vertices en cuestion)
			 */
			public void actionPerformed(ActionEvent e) {
				bloquearBotones();
				Ok5.setVisible(true);
				insertarVertices.setVisible(true);
				Cancel2.setVisible(true);
				A.setVisible(true);
				B.setVisible(true);
			}
		});
		eliminarCortos.setEnabled(false);
		eliminarCortos.setBounds(21, 356, 200, 76);
		frame.getContentPane().add(eliminarCortos);
//------------------------------------FIN BOTONES PRINCIPALES------------------------------------------
//------------------------------------BOTONES OK-------------------------------------------------------
		
		Ok = new JButton("Ok");
		Ok.addActionListener(new ActionListener() {
			/*
			 * Boton OK que servira para agregar vertices.
			 * Recibe un string del JPANE AgregadorVertices y verifica si esa string es una string valida
			 * Si no es valida, muestra un mensaje acorde
			 * Si es valida, muestra un mensaje acorde, agrega un vertice al grafo y agrega el rotulo del vertice a los 2 comboBox de rotulos
			 */
			public void actionPerformed(ActionEvent arg0) {
				if(!esNumero(AgregadorVertices.getText())){
					VentanaMensajes.setText("¡Rotulo ingresado invalido!");
				}
				else{
					int i =Integer.parseInt(AgregadorVertices.getText());				
					logica.agregarVertice(i);
					VentanaMensajes.setText("¡Vertice añadido correctamente, felicitaciones !");
					VerticesOrigen.addItem(i);
					VerticesDestino.addItem(i);
				}
			}
		});
		Ok.setBounds(491, 43, 127, 50);
		frame.getContentPane().add(Ok);
		Ok.setVisible(false);
		Ok.setEnabled(true);
		
		Ok2 = new JButton("Ok");
		Ok2.addActionListener(new ActionListener() {
			/*
			 * Boton OK que servira para agregar un arco entre 2 vertices
			 * Tomando los valores seleccionados de los 2 comboBox agrega un arco entre los vertices indicados y muestra un mensaje acorde
			 */
			public void actionPerformed(ActionEvent arg0) {
				int i = (int)VerticesOrigen.getSelectedItem();
				int j = (int)VerticesDestino.getSelectedItem();
				logica.agregarArco(logica.recuperarVertex(i) ,logica.recuperarVertex(j));
				VentanaMensajes.setText("¡Arco agregado correctamente, felicitaciones!");
				AgregadorVertices.setText("");
			}
		});
		Ok2.setVisible(false);
		Ok2.setBounds(264, 135, 133, 50);
		frame.getContentPane().add(Ok2);
		
		Ok3 = new JButton("Ok");
		Ok3.setVisible(false);
		Ok3.addActionListener(new ActionListener() {
			/*
			 * Boton OK que servira para encontrar un camino minimo entre dos vertices.
			 * Verifica los valores recibidos por los JPane A y B
			 * Si son invalidos, muestra un mensaje acorde y borra el contenido de los JPane
			 * Si son valido, muestra en la Ventana de Mensajes el camino mas corto si es que existe y  borra el contenido de los JPane
			 */
			public void actionPerformed(ActionEvent arg0) {
				if(!esNumero(A.getText()) || !esNumero(B.getText())){
					VentanaMensajes.setText("Algun rotulo ingresado no es valido");
					A.setText("");
					B.setText("");
				}else{
					VentanaMensajes.setText(logica.HallarCaminoMasCorto(Integer.parseInt(A.getText()),Integer.parseInt( B.getText())));
					A.setText("");
					B.setText("");
				}
				
			}
		});
		Ok3.setBounds(328, 227, 90, 30);
		frame.getContentPane().add(Ok3);
		
		Ok4 = new JButton("Ok");
		Ok4.setVisible(false);
		Ok4.addActionListener(new ActionListener() {
			/*
			 * Boton OK que servira para encontrar todos los caminos entre dos vertices.
			 * Verifica los valores recibidos por los JPane A y B
			 * Si son invalidos, muestra un mensaje acorde y borra el contenido de los JPane
			 * Si son valido, muestra en la Ventana de Mensajes todos los caminos existentes entre dichos vertices y borra el contenido de los JPane
			 */
			public void actionPerformed(ActionEvent arg0) {
				if(!esNumero(A.getText()) || !esNumero(B.getText())){
					VentanaMensajes.setText("Algun rotulo ingresado no es valido");
					A.setText("");
					B.setText("");
				}else{
					VentanaMensajes.setText(logica.HallarTodosCaminos(Integer.parseInt(A.getText()),Integer.parseInt( B.getText())));
					A.setText("");
					B.setText("");
				}
			}
		});
		Ok4.setBounds(328, 227, 90, 30);
		frame.getContentPane().add(Ok4);
		
		Ok5 = new JButton("Ok");
		Ok5.addActionListener(new ActionListener() {
			/*
			 * Boton OK que servira para eliminar el camino minimo entre dos vertices.
			 * Verifica los valores recibidos por los JPane A y B
			 * Si son invalidos, muestra un mensaje acorde y borra el contenido de los JPane
			 * Si son valido, muestra un mensaje de existencia o no del camino minimo, y borra de los comboBox los vertices de ese camino
			 */
			public void actionPerformed(ActionEvent e) {
				if(!esNumero(A.getText()) || !esNumero(B.getText())){
					VentanaMensajes.setText("Algun rotulo ingresado no es valido");
					A.setText("");
					B.setText("");
				}else{
					Queue<Integer> cola = logica.EliminarCaminoMinimo(Integer.parseInt(A.getText()),Integer.parseInt( B.getText()));
					if(cola.isEmpty()){ //Utilizamos esta cola, porque sino no podiamos borrar los valores de los vertices en los comboBox desde la clase logica.
						VentanaMensajes.setText("No existe el camino entre A y B, su camino minimo son unicamente sus vertices, o bien A o B no pertenecen al grafo");
					}else{
						try{
							while(!cola.isEmpty()){ 
								int borrar = cola.dequeue();
								boolean cortar = false;
								for (int i = 0; i < VerticesOrigen.getItemCount()&&!cortar; i++) {
								   if(VerticesOrigen.getItemAt(i)==borrar){
									   cortar= true;
									   VerticesOrigen.removeItemAt(i);
									   VerticesDestino.removeItemAt(i);
								   }
							   }
						}
						}catch(EmptyQueueException e1){
							System.out.println("Cola vacia");
						}				
					A.setText("");
					B.setText("");
					VentanaMensajes.setText("Camino corto eliminado correctamente");
				}
			}
		}
		});
		Ok5.setVisible(false);
		Ok5.setBounds(331, 227, 90, 30);
		frame.getContentPane().add(Ok5);
//------------------------------------FIN BOTONES OK-------------------------------------------------------		
//------------------------------------BOTONES CANCELAR-----------------------------------------------------
		Cancel = new JButton("Cancelar");
		Cancel.setVisible(false);
		Cancel.addActionListener(new ActionListener() {
			/*
			 * Boton CANCELAR que cancelara la accion que se estaba haciendo, hara invisible los botones, labels, comboBoxes, y JPanes auxiliares usados en ese momento
			 * y si hay almenos un vertice reactivara todos los botones principales (Excepto el de agregar grafo)
			 * Si no hay vertices, reactivara unicamente el boton para agregar vertices
			 */
			public void actionPerformed(ActionEvent arg0) {
				AgregadorVertices.setVisible(false);
				Ok.setVisible(false);
				label.setVisible(false);
				lBorrar.setVisible(false);
				AgregadorVertices.setText("");
				Cancel.setVisible(false);
				activarBotones();
				
			}
		});
		Cancel.setBounds(491, 135, 127, 50);
		frame.getContentPane().add(Cancel);
		
		Cancel2= new JButton("Cancelar");
		Cancel2.addActionListener(new ActionListener() {
			/*
			 * Boton CANCELAR que cancelara la accion que se estaba haciendo, hara invisible los botones, labels, y JPanes auxiliares usados en ese momento
			 * y reactivara todos los botones principales (Excepto el de agregar grafo)
			 *
			 */
			 public void actionPerformed(ActionEvent arg0) {
				 A.setText("");
				 B.setText("");
				 Cancel2.setVisible(false);
				 Ok3.setVisible(false);
				 Ok4.setVisible(false);
				 Ok5.setVisible(false);
				 A.setVisible(false);
				 B.setVisible(false);
				 activarBotones();
				 insertarVertices.setVisible(false);
			}
		});
		Cancel2.setVisible(false);
		Cancel2.setBounds(428, 227, 90, 30);
	    frame.getContentPane().add(Cancel2);
//------------------------------------FIN BOTONES CANCELAR-----------------------------------------------------
//------------------------------------BOTON BORRAR-------------------------------------------------------------
		borrar = new JButton("¡Borrar!");
		borrar.addActionListener(new ActionListener() {
			/*
			 * Tomara el valor del item seleccionado del combobox y eliminara el vertice con rotulo igual al item seleccionado
			 * Si se elimina el ultimo vertice, este boton, el cancelar, el combobox y el label se haran invisible y se activara unicamente el boton para agregar vertices
			 */
			public void actionPerformed(ActionEvent arg0) {
				int i =(int) VerticesOrigen.getSelectedItem();
				Vertex v = logica.recuperarVertex(i);
				int index = VerticesOrigen.getSelectedIndex();
				VerticesOrigen.removeItemAt(index);
				VerticesDestino.removeItemAt(index);
				logica.eliminarVertice(v);
				VentanaMensajes.setText("Elimino correctamente el vertice con rotulo :"+ v.element() +", ¡Felicitaciones!");
				if(VerticesOrigen.getSelectedIndex() == -1){
					borrar.setVisible(false);
					Cancel.setVisible(false);
					VerticesOrigen.setVisible(false);
					lBorrar.setVisible(false);
					agregarVertice.setEnabled(true);
				}
			}
		});
		borrar.setVisible(false);
		borrar.setBounds(264, 134, 133, 51);
		frame.getContentPane().add(borrar);
//-----------------------------------------------------------------------------------------------------
//---------------------------COMBOBOXES CONTENEDORES DE ROTULOS DE ARCO--------------------------------
		/*
		 * Los dos combobox serviran para seleccionar los vertices del grafo
		 */
		VerticesOrigen = new JComboBox<Integer>();
		VerticesOrigen.setBounds(254, 94, 158, 23);
		frame.getContentPane().add(VerticesOrigen);
		VerticesOrigen.setVisible(false);
		
		VerticesDestino = new JComboBox<Integer>();
		VerticesDestino.setVisible(false);
		VerticesDestino.setBounds(448, 94, 158, 23);
		frame.getContentPane().add(VerticesDestino);
		VerticesDestino.setVisible(false);
//---------------------------FIN COMBOBOXES -----------------------------------------------------------	

//---------------------------JPANELS-------------------------------------------------------------------
		
		/*
		 * Mostrara mensajes al usuario
		 */
		VentanaMensajes = new JTextPane();
		VentanaMensajes.setEditable(false);
		VentanaMensajes.setBounds(262, 257, 356, 154);
		frame.getContentPane().add(VentanaMensajes);
		
		/*
		 * JPane en el cual el usuario insertara el rotulo del vertice a agregar
		 */
		AgregadorVertices = new JTextPane();
		AgregadorVertices.setVisible(false);
		AgregadorVertices.setBounds(352, 51, 99, 20);
		frame.getContentPane().add(AgregadorVertices);
		
		/*
		 * 	 JPane en el cual el usuario insertara el rotulo ORIGEN del respectivo recorrido del grafo
		 */
		A = new JTextPane();
		A.setVisible(false);
		A.setBounds(274, 196, 101, 20);
		frame.getContentPane().add(A);
		
		/*
		 * 	 JPane en el cual el usuario insertara el rotulo DESTINO del respectivo recorrido del grafo
		 */
		B = new JTextPane();
		B.setVisible(false);
		B.setBounds(456, 196, 99, 20);
		frame.getContentPane().add(B);

// -----------------------------------FIN JPANELS--------------------------------------------
//------------------------------------LABELS ------------------------------------------------
		/*
		 * Label que indicara donde agregar los rotulos de los vertices
		 */
		insertarVertices = new JLabel("Ingrese el rotulo del vertice origen        Inserte el rotulo del vertice destino");
		insertarVertices.setVisible(false);
		insertarVertices.setBounds(231, 171, 418, 14);
		frame.getContentPane().add(insertarVertices);
		/*
		 * Label que indicara donde agregar un rotulo para crear un nuevo vertice
		 */
		label = new Label("¡Inserte el rotulo del vertice para poder crear uno nuevo aqui!");
		label.setVisible(false);
		label.setBounds(283, 20, 307, 22);
		frame.getContentPane().add(label);
		/*
		 * Label que indicara que se tiene tiene que elegir un vertice del Combobox para borrarlo
		 */					
		lBorrar = new JLabel("Elija el vertice a borrar!");
		lBorrar.setVisible(false);
		lBorrar.setBounds(279, 72, 133, 19);
		frame.getContentPane().add(lBorrar);
//-----------------------------------FIN LABELS------------------------------------------
				
				
		
		
	
		
}
	
//---------------------------METODOS PRIVADOS --------------------------------------------
	private boolean esNumero(String s){
		if(s == null || s.isEmpty())
			return false;
		
		for(int i = 0; i <s.length(); i++){
			if(!Character.isDigit(s.charAt(i)))
				return false;
		}
		return true;		
	}
	private void bloquearBotones(){
		agregarVertice.setEnabled(false);
		agregarArco.setEnabled(false);
		mostrarDFS.setEnabled(false);
		mostrarBFS.setEnabled(false);
		mostrarCorto.setEnabled(false);
		todosRecorridos.setEnabled(false);
		eliminarCortos.setEnabled(false);
		eliminar.setEnabled(false);
	}
	private void activarBotones(){
		agregarVertice.setEnabled(true);
		if(VerticesOrigen.getSelectedIndex() != -1){ //Verifica que el grafo tenga al menos 1 vertice
			agregarArco.setEnabled(true);
			mostrarDFS.setEnabled(true);
			mostrarBFS.setEnabled(true);
			mostrarCorto.setEnabled(true);
			borrar.setVisible(false);
			todosRecorridos.setEnabled(true);
			eliminarCortos.setEnabled(true);
			VentanaMensajes.setText("");
			eliminar.setEnabled(false);
			AgregadorVertices.setVisible(false);
			label.setVisible(false);
			Ok2.setVisible(false);
			VerticesOrigen.setVisible(false);
			VerticesDestino.setVisible(false);
			eliminar.setEnabled(true);
		}
	}
}
