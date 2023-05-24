package practica.libreria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//DUDAS REVISION: 
	//1 - Influye en algo tener muchos imports en una clase?
	//2 - lo de c[0] de iniciarSesion en la clase Cliente es buena idea?
	//3 - Esta bien poner los atributos para iniciar sesion como public static?

public class Ventana extends JFrame implements ActionListener{

	private JPanel jpPrincipal;
	
	private int contPP = 0;
	private static Cliente clienteActivo = null;
	
	private JButton iniciarSesion;
	private JButton agregarCesta;

	public Ventana() {
		
		//Panel
		jpPrincipal = new JPanel();
		jpPrincipal.setBackground(new Color(230, 230, 250));
		add(jpPrincipal);
		setContentPane(jpPrincipal);
		setLayout(null);
		
		
		//Tamaño ventana:
			//Voy a dividir la ventana en "trozos". Los primeros 320 px de X serán para el menú
		setSize(1280, 720);
		setResizable(false);
		
		crearTiendaPrincipal();
		
		//JLabel - Los nombre serán "p" de producto + Nombre del producto. MEJORABLE
		JLabel pOferta = crearJLabelMouseListener("Ofertas", 150);
		JLabel pEstuche = crearJLabelMouseListener("Estuches", 200);
		JLabel pLibro = crearJLabelMouseListener("Libros", 250);
		JLabel pLibreta = crearJLabelMouseListener("Libretas", 300);
		JLabel pBoligrafo = crearJLabelMouseListener("Boligrafos", 350);
		JLabel pCarpeta = crearJLabelMouseListener("Carpetas", 400);
		
		//JButton
		iniciarSesion = new JButton("Iniciar Sesion");
		agregarCesta = new JButton("CESTA");
		add(iniciarSesion);
		add(agregarCesta);
		
		//Coordenadas
		iniciarSesion.setBounds(1120, 20, 130, 30);
		agregarCesta.setBounds(1120, 50, 130, 30);
		
		//ActionListener
		iniciarSesion.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e1) {
				//JOptionPane.showMessageDialog(null, "Error extraño", null,	JOptionPane.ERROR_MESSAGE);
				
				if (iniciarSesion.getText().equals("CerrarSesion")) {
					
					clienteActivo=null;
					
					//System.out.println(clienteActivo.getNombre());
					
				} else {
				
					clienteActivo = Cliente.iniciarSesion();
				
				}
			
				//System.out.println(clienteActivo.getNombre());
				
			}
		
		});
		
		agregarCesta.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (Cliente.c==null) {
					
					JOptionPane.showMessageDialog(null, "ERROR\nAntes de acceder a la cesta tiene que iniciar sesion", null,JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					VentanaCesta vc = new VentanaCesta();
				
				}
			}
			
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	/*public static void cambioIniciarCerrarSesion() {
		
			//jpPrincipal.remove(iniciarSesion);
		if (clienteActivo==null) {
			
			System.out.println("Nulo");
			
		}
		
	    	iniciarSesion.setText("Cerrar Sesion");
			jpPrincipal.revalidate();
	    	jpPrincipal.repaint();
	    	
		
	}*/
	
	public JLabel crearJLabelMouseListener(String texto, int y) {
		
		JLabel jl = new JLabel(texto);
		add(jl);
		
		Font fuente = new Font("Arial", Font.PLAIN, 18);
		jl.setFont(fuente);
		
		jl.setForeground(Color.BLUE);
		
		//Para que las coordenadas entren bien.
		Dimension dimensiones = jl.getPreferredSize();
		
        int ancho = dimensiones.width;
        int alto = dimensiones.height;

		jl.setBounds(20, y, ancho, alto);
		
		jl.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {} //soltado
			public void mousePressed(MouseEvent e) {} //presionado
			public void mouseExited(MouseEvent e) { //cursor fuera
				
				jl.setForeground(Color.BLUE);
				
			}
			public void mouseEntered(MouseEvent e) { //cursor en él
				
				jl.setForeground(new Color(128, 0, 255));

			}
			public void mouseClicked(MouseEvent e) { //clic
				
				System.out.println(texto+" cliked");

			}
		});
		
		return jl;
		
	}
	
	public void crearTiendaPrincipal() {
		
		for (Producto producto : Producto.getListaProductos()) {
		
			JButton p = crearJButtonProductos(producto); //El JButton hace falta?
			
		}
		
	}
	
	public JButton crearJButtonProductos(Producto p) {
		
		/*
		 * La parte de "Tienda", empezará en 320 y acabará en 960(640) (Tambien hay que respetar márgenes)
		 * Los botones van a ser 125x75 de momento. Si le sumamos la altura del Label, 125x100.
		 * 
		 * De La parte de arriba quiero coger 180px minimo. Asi que tengo 540 de alto
		 * En total contamos con 640x540
		 * 
		 * Los botones ocuparán desde:
		 *  - X: 340 a 940 (340, 490, 640, 790)
		 * 
		 */
		int margenY = 200;
		int margenX = 150;
		
		int x = 340 + (contPP%4)*margenX;
		int y = 200 + (contPP/4)*margenY;
		
		JButton jb = new JButton();
		add(jb);
		
		JLabel etiqueta = new JLabel(p.getNombre());
		add(etiqueta); // System.out.println(x + " - " + y);
			
		jb.setBounds(x,y,125,75);
		etiqueta.setBounds(x,(y+75),125,25);
		
		contPP++;
		
		jb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				JFrame ventanaDeProducto = new JFrame(p.getNombre());
				JPanel panelVP = new JPanel();
				
				panelVP.setBackground(new Color(210, 210, 255));
				panelVP.setLayout(null);
				
				//Ventana
				ventanaDeProducto.setSize(640, 360);
				ventanaDeProducto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        ventanaDeProducto.setLocationRelativeTo(null);
				ventanaDeProducto.setLayout(null);
				//Componentes
				JLabel pDescripcion = new JLabel(p.getDescripcion());
				JLabel pImagen = new JLabel();
				JButton pComprar = new JButton("Comprar");
				JComboBox<Integer> pUnidades = new JComboBox<Integer>();
				
				panelVP.add(pDescripcion);
				panelVP.add(pComprar);
				panelVP.add(pUnidades);
				
				//JComboBox
				for (Integer i = 0; i<p.getStock(); i++) {
					pUnidades.addItem(i);
				}
				//JButton
				pComprar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						//Cuando se pulse este boton, quiero que se agregen a la cesta del cliente, pero antes de esto hay que terminar el inicio de sesion
						
					}
				});
				
				//Coordenadas (640x360. 300x para la foto)
				pUnidades.setBounds(270,180,100,25);
				pComprar.setBounds(370,180,25,25);
				pDescripcion.setBounds(30,210,300,25);
		        
				ventanaDeProducto.setContentPane(panelVP);
				ventanaDeProducto.setVisible(true);
			}
		});
		
		return jb;
		
	}

	public void crearVentanaDelProducto() {
		
		JFrame ventana2 = new JFrame("Ventana 2");
		ventana2.setSize(300, 200);
        ventana2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana2.setLocationRelativeTo(null);
        ventana2.setVisible(true);
        
	}

	public static void main(String[] args) { //En el futuro se usará la del principal
		
		Principal.insertarDatos();
		
		Ventana v = new Ventana();
		
	}
	
}
