package practica.libreria;

public class Libro extends Producto {
	
	private String editorial;
	private String tematica; //podria ser un char
	
	//Getters n Setters
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public String getTematica() {
		return tematica;
	}
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}
	
	//Constructores
	public Libro(String codigo, String nombre, String descripcion, double precio_unidad, int stock, String editorial,
			String tematica) {
		super(codigo, nombre, descripcion, precio_unidad, stock);
		this.editorial = editorial;
		this.tematica = tematica;
	}
	public Libro(String codigo, String nombre, String descripcion, double precio_unidad, int stock, String rutaImagen,
			String editorial, String tematica) {
		super(codigo, nombre, descripcion, precio_unidad, stock, rutaImagen);
		this.editorial = editorial;
		this.tematica = tematica;
	}
	@Override
	public String toString() {
		return "LIBRO [" + super.toString() + " | Editorial: " + editorial + " | Tematica: " + tematica + "]";
	}

}
