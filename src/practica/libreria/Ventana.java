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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

//DUDAS REVISION: 
	//1 - en pintarProductosCesta() de VentanaCesta, cuando total==0, como evito que salga la ventana de la cesta?

public class Ventana extends JFrame {
	
	private JPanel jpPrincipal, jpMenu;
	private JScrollPane jsp;
	private int contPP = 0;

	private static JButton iniciarSesion, cerrarSesion, agregarCesta;

	public Ventana() {
		
		//Tamaño ventana:
			//Voy a dividir la ventana en "trozos". Los primeros 320 px de X serán para el menú
		setSize(1280, 720);
		setResizable(false);
		
		//Paneles
		jpPrincipal = new JPanel();
		//jpPrincipal.setBounds(340, 180, 640, 540);
		jpMenu = new JPanel();
		jpMenu.setBounds(0,180,320,790);
		
		add(jpPrincipal);
		add(jpMenu);
		
		setLayout(null);
		jpPrincipal.setLayout(null);
		jpMenu.setLayout(null);
		
		encabezado();
		crearTiendaPrincipal();
		
		//JLabel - Los nombre serán "p" de producto + Nombre del producto. MEJORABLE
		crearJLabelMouseListenerProductos("Productos", 0);
		crearJLabelMouseListenerEstuche("Estuches", 50);
		crearJLabelMouseListenerLibro("Libros", 100);
		crearJLabelMouseListenerLibreta("Libretas", 150);
		crearJLabelMouseListenerBoligrafo("Boligrafos", 200);
		crearJLabelMouseListenerCarpeta("Carpetas", 250);
		
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
		agregarCesta.setBounds(1120, 51, 130, 50);
		
		//ActionListener
		iniciarSesion.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				VentanaInicioSesion vIS = new VentanaInicioSesion();
				
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
		

		//JScrollPane
		jsp = new JScrollPane(jpPrincipal);
		
		jsp.setBounds(340, 180, 640, 440); //El espacio que ocupa el panel dentro del frame
		
		add(jsp);
		
		jpPrincipal.setPreferredSize(new Dimension(620,Producto.getListaProductos().size()/4*180)); //Lo que va a ocupar el panel realmente
		jpPrincipal.setBackground(Principal.colorFondo);
		jpMenu.setBackground(Principal.colorFondo);
		getContentPane().setBackground(Principal.colorFondo);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public void encabezado() {

		JLabel lTexto = new JLabel("Libreria Jesulandro");
		lTexto.setFont(new Font("Old English Text MT", Font.BOLD, 34));
		lTexto.setBounds(100, 20, 450, 45);

		JTextField busqueda = new JTextField();
		busqueda.setBounds(420, 20, 450, 45);
		String text = busqueda.getText();

		JButton buscador = new JButton();
		buscador.setIcon(Producto.aplicarImagen("E:\\Programacion\\Eclipse\\Practica3\\src\\practica\\libreria\\Imagenes\\lupa.png", 44, 44));
		buscador.setBounds(868, 20, 44, 44);
		buscador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String text = busqueda.getText().toLowerCase();
				boolean productosEncontrados = false;

				contPP = 0;
				jpPrincipal.removeAll();
				
				for (Producto p : Producto.getListaProductos()) {
					
					if (p.getNombre().toLowerCase().contains(text)) {
						
						productosEncontrados = true;

						crearJButtonProductos(p);

					}
				}
				
				jpPrincipal.repaint();
				if (contPP == 0) {
					JOptionPane.showMessageDialog(null, "Sin coincidencias", "Error", JOptionPane.ERROR_MESSAGE);
				
				}
			}

		});

		add(lTexto);
		add(buscador);
		add(busqueda);

	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void activarCerrarSesion() {
		
		agregarCesta.setVisible(true);
		cerrarSesion.setVisible(true);
		iniciarSesion.setVisible(false);
		
	}
	
	public void crearJLabelMouseListenerLibro(String texto, int y) {
	    
		JLabel jl = new JLabel(texto);
		jpMenu.add(jl);
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
	        	
	        	contPP = 0;
	        	jpPrincipal.removeAll();
	        	
	        	for (Producto producto : Producto.getListaProductos()) {
	            
	        		if (producto instanceof Libro) {
	                
	        			crearJButtonProductos(producto);
	                
	        		}
	            
	        	}
	        	
	        	jpPrincipal.repaint();
	        	
	        }
	    });
	        
	}
	
	public void crearJLabelMouseListenerProductos(String texto, int y) {
    	
		JLabel jl = new JLabel(texto);
		jpMenu.add(jl);
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

	        	contPP = 0;
	        	jpPrincipal.removeAll();
	        	
	        	for (Producto producto : Producto.getListaProductos()) {
	            
	        		if (producto instanceof Producto) {
	                
	        			crearJButtonProductos(producto);
	                
	        		}
	            
	        	}
	        	
	        	jpPrincipal.repaint();
	        	
	        }
	    });
	        
	}
	
	public void crearJLabelMouseListenerEstuche(String texto, int y) {
    	
		JLabel jl = new JLabel(texto);
	    jpMenu.add(jl);
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
	        	
	        	contPP = 0;
	        	jpPrincipal.removeAll();
	        	
	        	for (Producto producto : Producto.getListaProductos()) {
	            
	        		if (producto instanceof Estuche) {
	                
	        			crearJButtonProductos(producto);
	                
	        		}
	            }
	        	
	        	jpPrincipal.repaint();
	        	
	        }
	    });
	        
	}
	
	public void crearJLabelMouseListenerCarpeta(String texto, int y) {
    	
		JLabel jl = new JLabel(texto);
		jpMenu.add(jl);
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
	        	
	        	contPP = 0;
	        	jpPrincipal.removeAll();
	        	
	        	for (Producto producto : Producto.getListaProductos()) {
	            
	        		if (producto instanceof Carpeta) {
	                
	        			crearJButtonProductos(producto);
	                
	        		}
	            }
	        	
	        	jpPrincipal.repaint();
	        	
	        }
	    });
	        
	}
	
	public void crearJLabelMouseListenerLibreta(String texto, int y) {
    	
		JLabel jl = new JLabel(texto);
		jpMenu.add(jl);
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
	        	
	        	contPP = 0;
	        	jpPrincipal.removeAll();
	        	
	        	for (Producto producto : Producto.getListaProductos()) {
	            
	        		if (producto instanceof Libreta) {
	                
	        			crearJButtonProductos(producto);
	                
	        		}
	            }
	        	
	        	jpPrincipal.repaint();
	        	
	        }
	    });
	        
	}
	
	public void crearJLabelMouseListenerBoligrafo(String texto, int y) {
    	
		JLabel jl = new JLabel(texto);
		jpMenu.add(jl);
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
	        	
	        	contPP = 0;
	        	jpPrincipal.removeAll();
	        	
	        	for (Producto producto : Producto.getListaProductos()) {
	            
	        		if (producto instanceof Boligrafo) {
	                
	        			crearJButtonProductos(producto);
	                
	        		}
	            
	        	}
	        	
	        	jpPrincipal.repaint();
	        	
	        }
	    });
	        
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
		
		int x = 20+(contPP%4)*margenX;
		int y = (contPP/4)*margenY;
		
		int w = 125; //width - anchura del boton
		int h = 75; //height - altura del boton
		
		JButton botonProducto = new JButton();
		ImageIcon iconoProducto = Producto.aplicarImagen(p.getRutaImagen(), 125, 75);
		botonProducto.setIcon(iconoProducto);
		jpPrincipal.add(botonProducto);
		
		JLabel etiqueta = new JLabel(p.getNombre());
		jpPrincipal.add(etiqueta); // System.out.println(x + " - " + y);
			
		botonProducto.setBounds(x,y,w,h);
		etiqueta.setBounds(x,(y+h),w,25);
		
		contPP++;
		
		botonProducto.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			
				VentanaProducto vp = new VentanaProducto(p);
				
			}
			
		});
		
	}
	
}
