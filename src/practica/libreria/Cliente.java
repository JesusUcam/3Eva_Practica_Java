package practica.libreria;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Cliente {
	
	//Se pueden añadir mas atributos
	private String nombre;
	private String telefono;
	
	private ArrayList<Producto> cesta;
	
	private static ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
	public static Cliente c = null;
	
	
	//Getters n Setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public ArrayList<Producto> getCesta() {
		return cesta;
	}
	public void setCesta(ArrayList<Producto> cesta) {
		this.cesta = cesta;
	}
	public static ArrayList<Cliente> getListaCliente() {
		return listaCliente;
	}
	public static void setListaCliente(ArrayList<Cliente> listaCliente) {
		Cliente.listaCliente = listaCliente;
	}
	
	//Constructores
	public Cliente() {}
	public Cliente(String nombre, String telefono) {
		
		this.nombre = nombre;
		this.telefono = telefono;
		
		this.cesta = new ArrayList<Producto>();
		
	}
	@Override
	public String toString() {
		return "Cliente: " + super.toString() + " [nombre=" + nombre + ", telefono=" + telefono + "]";
	}
	
	//Metodos
	public static Cliente iniciarSesion() {
		
		JFrame jfInicioSesion = new JFrame();
		JPanel jpInicioSesion = new JPanel();
		
		jpInicioSesion.setBackground(new Color(210, 210, 255));
		jpInicioSesion.setLayout(null);
		
		//Ventana
		jfInicioSesion.setSize(200, 200);
		jfInicioSesion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jfInicioSesion.setLocationRelativeTo(null);
		jfInicioSesion.setLayout(null);
		
		JLabel jlNombre = new JLabel("Introduzca su nombre:");
		JLabel jlTelefono = new JLabel("Introduzca su telefono:");
		JTextField jtfNombre = new JTextField();
		JTextField jtfTelefono = new JTextField();
		JButton jbVerificar = new JButton("Verificar");
		
		jpInicioSesion.add(jlNombre);
		jpInicioSesion.add(jlTelefono);
		jpInicioSesion.add(jtfNombre);
		jpInicioSesion.add(jtfTelefono);
		jpInicioSesion.add(jbVerificar);
		
		//Coordenadas
		jlNombre.setBounds(25,10,130,25);
		jtfNombre.setBounds(25,35,130,25);
		jlTelefono.setBounds(25,60,130,25);
		jtfTelefono.setBounds(25,85,130,25);
		jbVerificar.setBounds(25,120,100,30);
		
		jfInicioSesion.setContentPane(jpInicioSesion);
		jfInicioSesion.setVisible(true);
		
		
		
		jbVerificar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				
				
				if (jtfNombre.getText().isEmpty()||jtfTelefono.getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "ERROR. No puedes dejar campos en blanco.\nRellena todos los campos y dale a enviar", null,	JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					String nombre = jtfNombre.getText();
					String telefono = jtfTelefono.getText();
					
					for (Cliente clienteIS : listaCliente) {
			
						if(clienteIS.getNombre().equalsIgnoreCase(nombre) && clienteIS.getTelefono().equals(telefono)) {
							
							c = clienteIS;
								
						}
					
					}
					
					if (c == null) {
						
						int nuevoCliente = JOptionPane.showConfirmDialog(null, "Los datos introducidos no corresponden con ninguno de nuestros clientes \n¿Desea registrarse?", "Crear usuario", JOptionPane.YES_NO_OPTION);
						if (nuevoCliente == JOptionPane.YES_OPTION) {
							
							c = new Cliente(nombre, telefono);
							listaCliente.add(c);
					
							System.out.println(c.getNombre());
							
						    JOptionPane.showMessageDialog(null, "¡Cliente registrado! disfrute sus compras");
						    
						    //Ventana.cambioIniciarCerrarSesion();
						    
						    jfInicioSesion.dispose();
						    
						} //else { JOptionPane.showMessageDialog(null, "Operacion cancelada");}
						
					} else {
						
						JOptionPane.showMessageDialog(null, "Bienvenid@ "+c.getNombre()+", esperamos que encuentre lo que busca");
						jfInicioSesion.dispose();
						
					}
				}
				
			}
		});
		
		
		return c; //no hace falta
		
	}
	
	public static void mostrarCesta(Cliente c) {
		
		for (Producto productoCesta : c.getCesta()) {
			
			System.out.println(productoCesta);
			
		}
		
	}
	
	public static void pagoCesta(Cliente c) {
		
		String factura = "\nFACTURA CESTA LIBRERIA\nNombre: "+c.nombre+" - Tlf: "+c.telefono+"\n";
		
		double total = 0;
		
		if (c.cesta.size()>0) {
			
			for (Producto productoCesta : c.getCesta()) {
					
				factura = factura + "Producto: " + productoCesta.getNombre() + " - Precio: " + productoCesta.getPrecioUnidad() + "€\n";
				
				total = total + productoCesta.getPrecioUnidad();
					
			}
			
			factura = factura + "TOTAL: " + total + "€\n";
			System.out.println(factura);
			
			c.cesta.clear();
			
		} else {
			
			System.out.println("Todavia no tiene ningun producto en su cesta. Vaya a nuestra opción 2 para comprar.");
			
		}
	
	}
	
}
