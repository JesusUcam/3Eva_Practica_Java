package practica.libreria;

public class Libreta extends Producto {
	
	private int numHojas; 
	private double tamanyo; //cm 
	private String color;
	
	//Getters n Setters
	public int getNumHojas() {
		return numHojas;
	}
	public void setNumHojas(int numHojas) {
		this.numHojas = numHojas;
	}
	public double getTamaño() {
		return tamanyo;
	}
	public void setTamaño(double tamaño) {
		this.tamanyo = tamaño;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	//Constructores
	public Libreta(String codigo, String nombre, String descripcion, double precio_unidad, int stock, int numHojas,
			float tamaño, String color) {
		super(codigo, nombre, descripcion, precio_unidad, stock);
		this.numHojas = numHojas;
		this.tamanyo = tamaño;
		this.color = color;
	}
	@Override
	public String toString() {
		return "LIBRETA [" + super.toString() + " | Numero de Hojas: " + numHojas + " | Tamaño: " + tamanyo + " | Color: " + color + "]";
	}
	
}
