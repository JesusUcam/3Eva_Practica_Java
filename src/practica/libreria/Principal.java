package practica.libreria;

import java.awt.Color;
import java.util.Scanner;

public class Principal {
	
	public static Color colorFondo = new Color(230, 230, 250);

	//meter aqui los datos de prueba en otro metodo y crear un escaner con control de errores para el menu
	public static void insertarDatos() {
		
		//Hemos puesto los codigos de los productos como un sólo número para simplificarlo.
		
		//BOLIGRAFOS: codigo, nombre, descripcion, precioUnidad, stock, puntaFina, color
		Boligrafo boligrafo1 = new Boligrafo("1", "Bic Azul", 
				"Un ícono de confiabilidad y estilo. Su diseño atemporal se desliza suavemente, dejando trazos legibles y duraderos. Perfecto para notas, escritura a mano y plasmar ideas con confianza. ¡Elige calidad y simplicidad con el bolígrafo Bic azul clásico!", 
				0.25, 30, "E:\\Programacion\\Eclipse\\Practica3\\src\\practica\\libreria\\Imagenes\\BicAzul.png",
				0.5, "azul");
		Boligrafo boligrafo2 = new Boligrafo("2", "Bic Rojo", 
				"Un ícono de confiabilidad y estilo. Su diseño atemporal se desliza suavemente, dejando trazos legibles y duraderos. Perfecto para notas, escritura a mano y plasmar ideas con confianza. ¡Elige calidad y simplicidad con el bolígrafo Bic rojo clásico!", 
				0.25, 4, "E:\\Programacion\\Eclipse\\Practica3\\src\\practica\\libreria\\Imagenes\\BicRojo.png",
				0.5, "rojo");
		Producto.getListaProductos().add(boligrafo2);
		Producto.getListaProductos().add(boligrafo1);
		
		//LIBRETAS: codigo, nombre, descripcion, precioUnidad, stock, numHojas, tamanyo, color
		Libreta libreta1 = new Libreta("3", "libreta Roja", 
				"La libreta roja Oxford destaca por su cubierta resistente y llamativa. Sus hojas de alta calidad permiten escribir con suavidad y claridad. Con un diseño de espiral, facilita el uso y la organización de tus notas. Una opción elegante y confiable para tus apuntes diarios.", 
				2.2, 19, "E:\\Programacion\\Eclipse\\Practica3\\src\\practica\\libreria\\Imagenes\\LibretaRoja.png", 
				90, 30, "rojo");
		Producto.getListaProductos().add(libreta1);
		
		//CARPETAS: codigo, nombre, descripcion, precioUnidad, stock, conSeparadores
		Carpeta carpeta1 = new Carpeta("4", "Carpeta Verde", 
				"La carpeta verde sencilla es una joya de la simplicidad y practicidad. Su diseño minimalista y elegante te brinda un lienzo en blanco para organizar tus documentos de manera impecable. Con su durabilidad y funcionalidad, te acompañará en todas tus aventuras y proyectos. ¡Descubre la belleza en la sencillez con esta increíble carpeta verde!", 
				1, 7, "E:\\Programacion\\Eclipse\\Practica3\\src\\practica\\libreria\\Imagenes\\CarpetaVerde.png",
				false);
		Producto.getListaProductos().add(carpeta1);

		//ESTUCHE: codigo, nombre, descripcion, precioUnidad, stock, tipo, numDeColores
		Estuche estuche1 = new Estuche("5", "Estuche", 
				"El estuche económico y de precio accesible es una solución práctica y funcional para guardar tus objetos escolares. Aunque su precio es bajo, cumple su propósito básico de mantener tus útiles organizados y protegidos. Una opción económica para aquellos que buscan una solución sencilla y asequible.", 
				1, 20, "E:\\Programacion\\Eclipse\\Practica3\\src\\practica\\libreria\\Imagenes\\Estuche.png", 
				"pequeño", 1);
		Estuche estuche2 = new Estuche("6", "Estuche pro", 
				"El estuche Gucci de máxima calidad es un símbolo de lujo y estilo. Confeccionado con materiales de primera calidad, ofrece durabilidad y elegancia en cada detalle. Perfecto para guardar tus pertenencias con estilo y distinción.\n¡¡¡SOLO PARA PROFESIONALES!!!", 
				89.99, 3, "E:\\Programacion\\Eclipse\\Practica3\\src\\practica\\libreria\\Imagenes\\EstuchePro.png", 
				"pro", 5);
		Producto.getListaProductos().add(estuche1);
		Producto.getListaProductos().add(estuche2);
		
		//LIBRO: codigo, nombre, descripcion, precioUnidad, stock, editorial, tematica
		Libro libro1 = new Libro("7", "Matematicas 2º ESO", 
				"El libro de matemáticas de 2º de la ESO es una herramienta educativa esencial. Con contenido exhaustivo y didáctico, brinda conocimientos fundamentales en matemáticas. Ideal para fortalecer habilidades numéricas y resolver problemas de forma efectiva.", 
				9.99, 16, "E:\\Programacion\\Eclipse\\Practica3\\src\\practica\\libreria\\Imagenes\\LibroMatematicas.png", 
				"Santillana", "Educativo");
		Libro libro2 = new Libro("8", "500 Chistes", 
				"El libro recopilatorio de chistes es una fuente de risas y entretenimiento sin fin. Reúne una variedad de chistes ingeniosos y divertidos que te harán reír a carcajadas. Perfecto para alegrar cualquier momento y compartir risas con amigos y familiares.", 
				7.99, 2, "E:\\Programacion\\Eclipse\\Practica3\\src\\practica\\libreria\\Imagenes\\LibroChistes.png", 
				"A", "Humor");
		Producto.getListaProductos().add(libro1);
		Producto.getListaProductos().add(libro2);
		
		//Clientes
		Cliente c1 = new Cliente("Belen Saez Baeza", "111222333");
		Cliente c2 = new Cliente("Alvaro Martinez Cuenca", "666333111");
		Cliente c3 = new Cliente("1", "1");
		Cliente.getListaCliente().add(c1);
		Cliente.getListaCliente().add(c2);
		Cliente.getListaCliente().add(c3);
		
		//Cesta
		c3.getCesta().add(estuche1);
		c3.getCesta().add(estuche1);
		c3.getCesta().add(boligrafo1);
		c3.getCesta().add(boligrafo1);
		c3.getCesta().add(boligrafo2);
		c3.getCesta().add(boligrafo2);
		c2.getCesta().add(libro2);
		c2.getCesta().add(libro1);
		
	}

	public static int excepcionInt() {
		
		Scanner sc = new Scanner(System.in);
		
		boolean key = false;
		int x = 0;
		
		while (!key) {
			
			try {
			
				x = sc.nextInt();		
				key = true;
				
			} catch (Exception e){
				
				System.out.println("Número NO válido. Por favor introduzca un numero");
				sc.next();
				
			}
	
		}
		
		return x;
		
	}

	public static String stringScaner() {
		
		Scanner sc = new Scanner(System.in);
		
		String texto = sc.nextLine();
		
		return texto;
		
	}
	
	//Falta hacer bien el formato del toString

	public static void main(String[] args) {
		
		insertarDatos();

		Ventana v = new Ventana();
		
	}

}
