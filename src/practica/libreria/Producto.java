package practica.libreria;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Producto {
	
	private String codigo;
	private String nombre;
	private String descripcion;
	private double precioUnidad;
	private int stock;
	private String rutaImagen;
	
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
	public String getRutaImagen() {
		return rutaImagen;
	}
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	//Constructores
	public Producto(String codigo, String nombre, String descripcion, double precio_unidad, int stock) {
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioUnidad = precio_unidad;
		this.stock = stock;
		this.rutaImagen = "E:\\Programacion\\Eclipse\\Practica3\\src\\practica\\libreria\\Imagenes\\sin_imagen.png";

	}
	public Producto(String codigo, String nombre, String descripcion, double precio_unidad, int stock, String rutaImagen) {
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioUnidad = precio_unidad;
		this.stock = stock;
		this.rutaImagen = rutaImagen;
		
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
	
	public static Producto buscadorProductos(String codigoP) {
		
		Producto p = null;
		
		for (Producto producto : Producto.getListaProductos()) {
			
			if (producto.codigo.equalsIgnoreCase(codigoP)){
				
				p = producto;
				
			}
			
			System.out.println(producto.codigo);
		}
		
		return p;
		
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
		
		}
		
	}
	
	public static ImageIcon aplicarImagen(Producto p, int x, int y) {
		
		ImageIcon imagenProducto = new ImageIcon(p.rutaImagen);
		Image imagen = imagenProducto.getImage();
		Image nuevaImagen = imagen.getScaledInstance(x, y, Image.SCALE_DEFAULT);
		ImageIcon imagenFinal = new ImageIcon(nuevaImagen);
		
		return imagenFinal;
		
	}
	
}
