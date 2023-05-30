package practica.libreria;

public class Carpeta extends Producto {
	
	//se pueden añadir mas atributos
	private boolean conSeparadores; //a juzgar por el nombre, le he puesto boolean pero creo que seria mejor un int para asi saber el num de separadores en caso de que tenga

	//Getters n Setters
	public boolean isConSeparadores() {
		return conSeparadores;
	}

	public void setConSeparadores(boolean conSeparadores) {
		this.conSeparadores = conSeparadores;
	}

	//Constructores
	public Carpeta(String codigo, String nombre, String descripcion, double precio_unidad, int stock,
			boolean conSeparadores) {
		super(codigo, nombre, descripcion, precio_unidad, stock);
		this.conSeparadores = conSeparadores;
	}

	public Carpeta(String codigo, String nombre, String descripcion, double precio_unidad, int stock, String rutaImagen,
			boolean conSeparadores) {
		super(codigo, nombre, descripcion, precio_unidad, stock, rutaImagen);
		this.conSeparadores = conSeparadores;
	}

	@Override
	public String toString() {
		
		if (conSeparadores) {
			return "CARPETA [" + super.toString() + " | Con Separadores]";
		} else {
			return "CARPETA [" + super.toString() + " | Sin Separadores]";
		}
		
	}
	
	
	
}
