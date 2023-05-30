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
}
