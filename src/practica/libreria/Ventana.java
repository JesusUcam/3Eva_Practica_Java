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
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//DUDAS REVISION: 
	//1 - en pintarProductosCesta() de VentanaCesta, cuando total==0, como evito que salga la ventana de la cesta?

public class Ventana extends JFrame implements ActionListener{

	public static Class<?> clase = null;
	private JPanel jpPrincipal;
	
	private int contPP = 0;
	
	//Espero que esto esté bien, no había otra opción
	private static JButton iniciarSesion, cerrarSesion, agregarCesta;

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
		JLabel pProductos = crearJLabelMouseListener("Productos", 150);
		JLabel pEstuche = crearJLabelMouseListener("Estuches", 200);
		JLabel pLibro = crearJLabelMouseListener("Libros", 250);
		JLabel pLibreta = crearJLabelMouseListener("Libretas", 300);
		JLabel pBoligrafo = crearJLabelMouseListener("Boligrafos", 350);
		JLabel pCarpeta = crearJLabelMouseListener("Carpetas", 400);
		
		//JButton
		iniciarSesion = new JButton("Iniciar Sesion");
		cerrarSesion = new JButton("Cerrar Sesion");
		agregarCesta = new JButton("CESTA");
		
		add(cerrarSesion);
		add(agregarCesta);
		add(iniciarSesion);
			
		if (Cliente.c==null) {
			
			iniciarSesion.setVisible(true);
			agregarCesta.setVisible(false);
			cerrarSesion.setVisible(false);
		
		} else {
			
			agregarCesta.setVisible(true);
			cerrarSesion.setVisible(true);
			iniciarSesion.setVisible(false);
			
		}
		
		//Coordenadas
		iniciarSesion.setBounds(1120, 20, 130, 30);
		cerrarSesion.setBounds(1120, 20, 130, 30);
		agregarCesta.setBounds(1120, 50, 130, 30);
		
		//ActionListener
		iniciarSesion.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Cliente.iniciarSesion();
				
			}
		
		});
		
		cerrarSesion.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Cliente.c=null;
				
				iniciarSesion.setVisible(true);
				agregarCesta.setVisible(false);
				cerrarSesion.setVisible(false);
				
			}

		});
		
		agregarCesta.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (Cliente.c==null) {
					
					JOptionPane.showMessageDialog(null, "ERROR\nAntes de acceder a la cesta tiene que iniciar sesion", null,JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					if (Cliente.c.getCesta().isEmpty()) {
						
						JOptionPane.showMessageDialog(null, "Su cesta esta vacía, puede agregar productos desde nuestra tienda", "Cesta vacia" ,JOptionPane.ERROR_MESSAGE);
					
					} else {
						
						VentanaCesta vc = new VentanaCesta();
						
					}
				
				}
			}
			
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void activarCerrarSesion() {
		
		agregarCesta.setVisible(true);
		cerrarSesion.setVisible(true);
		iniciarSesion.setVisible(false);
		
	}
	
	public JLabel crearJLabelMouseListener(String texto, int y) {
	    	
		JLabel jl = new JLabel(texto);
	    add(jl);
	    Font fuente = new Font("Arial", Font.PLAIN, 18);
	    jl.setFont(fuente);
	    jl.setForeground(Color.BLUE);
	    
	    Dimension dimensiones = jl.getPreferredSize();
	    int ancho = dimensiones.width;
	    int alto = dimensiones.height;
	    jl.setBounds(20, y, ancho, alto);
	    
	    jl.addMouseListener(new MouseListener() {
	    	
	    	public void mouseReleased(MouseEvent e) {}
	    	public void mousePressed(MouseEvent e) {}
	    	public void mouseExited(MouseEvent e) {
	    	
	    		jl.setForeground(Color.BLUE);
	            
	            }
	            
	    	public void mouseEntered(MouseEvent e) {
	        
	        	jl.setForeground(new Color(128, 0, 255));
	        
	        }
	        public void mouseClicked(MouseEvent e) {
	        
	        	for (Producto producto : Producto.getListaProductos()) {
	            
	        		if (producto instanceof Libro) {
	                
	        			crearJButtonProductos(producto);
	                
	        		}
	            }
	        }
	    });
	        
	    return jl;
	        
	}
	
	public void crearTiendaPrincipal() {
		
		for (Producto producto : Producto.getListaProductos()) {
		
			crearJButtonProductos(producto);
			
		}
		
	}
	
	public void crearJButtonProductos(Producto p) {
		
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
		
		int w = 125; //width - anchura del boton
		int h = 75; //height - altura del boton
		
		JButton botonProducto = new JButton();
		ImageIcon iconoProducto = new ImageIcon(p.getRutaImagen());		
		botonProducto.setIcon(iconoProducto);
		add(botonProducto);
		
		JLabel etiqueta = new JLabel(p.getNombre());
		add(etiqueta); // System.out.println(x + " - " + y);
			
		botonProducto.setBounds(x,y,w,h);
		etiqueta.setBounds(x,(y+h),w,25);
		
		contPP++;
		
		botonProducto.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			
				VentanaProducto vp = new VentanaProducto(p);
				
			}
			
		});
		
	}

	public static void main(String[] args) { //En el futuro se usará la del principal
		
		Principal.insertarDatos();
		
		Ventana v = new Ventana();
		
	}
	
}
