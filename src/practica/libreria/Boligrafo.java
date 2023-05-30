package practica.libreria;

public class Boligrafo extends Producto {
	
	private double puntaFina;
	private String color;
	
	//Getters n Setters
	public double getPuntaFina() {
		return puntaFina;
	}
	public void setPuntaFina(double puntaFina) {
		this.puntaFina = puntaFina;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	//Constructores
	public Boligrafo(String codigo, String nombre, String descripcion, double precio_unidad, int stock,
			String rutaImagen, double puntaFina, String color) {
		super(codigo, nombre, descripcion, precio_unidad, stock, rutaImagen);
		this.puntaFina = puntaFina;
		this.color = color;
	}

	public Boligrafo(String codigo, String nombre, String descripcion, double precio_unidad, int stock,
			double puntaFina, String color) {
		super(codigo, nombre, descripcion, precio_unidad, stock);
		this.puntaFina = puntaFina;
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "BOLIGRAFO [" + super.toString() + " | Punta: " + puntaFina + " | Color: " + color + "]"; 
	}

}
