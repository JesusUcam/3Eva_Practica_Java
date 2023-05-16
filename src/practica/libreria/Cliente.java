package practica.libreria;

import java.util.ArrayList;
import java.util.Arrays;

public class Cliente {
	
	//Se pueden añadir mas atributos
	private String nombre;
	private String telefono;
	
	private ArrayList<Producto> cesta;
	
	private static ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
	
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
		
		Cliente c = null;
		
		System.out.print("Introduzca su nombre: ");
		String nombre = Principal.stringScaner();
		
		System.out.print("Introduzca su numero de telefono: ");
		String telefono = Principal.stringScaner();
		
		for (Cliente clienteIS : listaCliente) {
			
			if(clienteIS.getNombre().equalsIgnoreCase(nombre) && clienteIS.getTelefono().equals(telefono)) {
				
				c = clienteIS;
					
			}
		
		}
		
		if (c==null) {
			
			c = new Cliente(nombre, telefono);
			getListaCliente().add(c);
			
			System.out.println("¡Sus datos han sido registrados correctamente! Ya puede comprar en nuestra libreria");
			
		}
		
		return c;
		
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
