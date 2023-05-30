package practica.libreria;

public class Estuche extends Producto {
	
	private String tipo; //(de lapices, rotuladores, temperas). Podria ser un char
	private int numDeColores;
	
	//Getters n Setters
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getNumDeColores() {
		return numDeColores;
	}
	public void setNumDeColores(int numDeColores) {
		this.numDeColores = numDeColores;
	}

	//Contructores
	public Estuche(String codigo, String nombre, String descripcion, double precio_unidad, int stock, String tipo,
			int numDeColores) {
		super(codigo, nombre, descripcion, precio_unidad, stock);
		this.tipo = tipo;
		this.numDeColores = numDeColores;
		
	}
	public Estuche(String codigo, String nombre, String descripcion, double precio_unidad, int stock, String rutaImagen,
			String tipo, int numDeColores) {
		super(codigo, nombre, descripcion, precio_unidad, stock, rutaImagen);
		this.tipo = tipo;
		this.numDeColores = numDeColores;
	}
	@Override
	public String toString() {
		return "ESTUCHE [" + super.toString() + " | Tipo: " + tipo + " | Colores: " + numDeColores + "]"; 
	}
	
}
