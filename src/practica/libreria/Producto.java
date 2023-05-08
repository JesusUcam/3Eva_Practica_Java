package practica.libreria;

import java.util.ArrayList;

public class Producto {
	
	private String codigo;
	private String nombre;
	private String descripcion;
	private double precioUnidad;
	private int stock;
	
	private static ArrayList<Producto> listaProductos = new ArrayList<Producto>();
	
	//Getters n Setters
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecioUnidad() {
		return precioUnidad;
	}
	public void setPrecioUnidad(double precio_unidad) {
		this.precioUnidad = precio_unidad;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public static ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}
	public static void setListaProductos(ArrayList<Producto> listaProductos) {
		Producto.listaProductos = listaProductos;
	}
	
	//Constructores
	public Producto(String codigo, String nombre, String descripcion, double precio_unidad, int stock) {
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioUnidad = precio_unidad;
		this.stock = stock;
		
	}
	@Override
	public String toString() {
		return "Codigo: " + codigo + " | Nombre: " + nombre + " | Descripcion: " + descripcion + " | Precio Unidad: "
				+ precioUnidad + " | Stock: " + stock;
	}
	
	//Metodos
	public static void lectorProductos() {
		
		for (Producto producto : listaProductos) {
			
			System.out.println(producto.toString());
			
		}
		
	}
	
	public static void comprarProductos(Cliente c) {

		System.out.println("Introduzca el código del producto que desea comprar. Cuando desee dejar de comprar escriba \"0\""); //Falta que tenga en cuenta el stock
		String codProducto = "";
		
		while(!codProducto.equals("0")) {

			Producto p = null;
			
			codProducto = Principal.stringScaner();
		
			for (Producto producto : listaProductos) {
			
				if (producto.getCodigo().equals(codProducto)) {
				
					p = producto;
				
				}
			
			}
			
			if (p==null) {
				
				System.out.println("Producto no encontrado... pruebe con otro");
				
			} else {
				
				if(p.stock>0) {
				
					c.getCesta().add(p); 
					
					p.setStock(p.stock-1);
					
					System.out.println("Producto agregado al carrito");
				
				} else {
					
					System.out.println("Producto agotado.");
					
				}
				
			}
			
			if(codProducto=="manin"){

				System.out.println("Me detecta");
			}
		}
		
	}
	
}
