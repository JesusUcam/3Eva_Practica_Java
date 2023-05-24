package practica.libreria;

import java.util.Scanner;

public class Principal {

	//meter aqui los datos de prueba en otro metodo y crear un escaner con control de errores para el menu
	public static void insertarDatos() {
		
		//Hemos puesto los codigos de los productos como un sólo número para simplificarlo.
		
		//BOLIGRAFOS: codigo, nombre, descripcion, precioUnidad, stock, puntaFina, color
		Boligrafo boligrafo1 = new Boligrafo("1", "BicAzul", "un clasico de los boligrafos azules", 0.22, 30, 0.5, "azul");
		Boligrafo boligrafo2 = new Boligrafo("2", "BicRojo", "esencial para profesores", 0.25, 4, 0.5, "rojo");
		Producto.getListaProductos().add(boligrafo2);
		Producto.getListaProductos().add(boligrafo1);
		
		//LIBRETAS: codigo, nombre, descripcion, precioUnidad, stock, numHojas, tamanyo, color
		Libreta libreta1 = new Libreta("3", "libreta Roja", "Libreta de anillas Oxford, alta calidad.", 2.2, 19, 90, 30, "rojo");
		Producto.getListaProductos().add(libreta1);
		
		//CARPETAS: codigo, nombre, descripcion, precioUnidad, stock, conSeparadores
		Carpeta carpeta1 = new Carpeta("4", "Carpeta Verde", "Carpeta grande low cost", 1, 7, false);
		Producto.getListaProductos().add(carpeta1);

		//ESTUCHE: codigo, nombre, descripcion, precioUnidad, stock, tipo, numDeColores
		Estuche estuche1 = new Estuche("5", "Estuche", "Estuche low cost", 1, 20, "pequeño", 1);
		Estuche estuche2 = new Estuche("6", "Estuche pro", "Estuche de alta calidad para profesionales", 89.99, 0, "pro", 5);
		Producto.getListaProductos().add(estuche1);
		Producto.getListaProductos().add(estuche2);
		
		//LIBRO: codigo, nombre, descripcion, precioUnidad, stock, editorial, tematica
		Libro libro1 = new Libro("7", "Matematicas 2º ESO", "Para alumnos de 2º de la ESO", 9.99, 16, "Santillana", "Educativo");
		Libro libro2 = new Libro("8", "500 Chistes", "Recopilacion con los mejores chistes en español", 7.99, 2, "A", "Humor");
		Producto.getListaProductos().add(libro1);
		Producto.getListaProductos().add(libro2);
		
		//Clientes
		Cliente c1 = new Cliente("Belen Saez Baeza", "111222333");
		Cliente c2 = new Cliente("Alvaro Martinez Cuenca", "666333111");
		Cliente c3 = new Cliente("ana", "1");
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
		
		//IMPORTANTE BORRAR ESTO CUANDO ENTREGE EL CODIGO
		Cliente.c = c3;
		
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
		
		final String menu = 
				"1. mostrar datos de los productos"
				+ "\n2. comprar productos"
				+ "\n3. mostrar lista compra"
				+ "\n4. pagar" //Se cobra todo al cliente, se limpia la cesta y se le imprime un ticket
				+ "\n5. Salir";
		
		boolean key = false;
		while (!key) {
			
			System.out.println(menu);
			
			int menuOpcion = excepcionInt();
			switch (menuOpcion) {
			case 1:
				
				Producto.lectorProductos();
				
				break;
			case 2:
				
				Producto.comprarProductos(Cliente.iniciarSesion());
	
				break;
			case 3:
				
				Cliente.mostrarCesta(Cliente.iniciarSesion());
	
				break;
			case 4:
				
				Cliente.pagoCesta(Cliente.iniciarSesion());
				
				break;
			case 5:
				
				key = true;
				System.out.println("Que tenga un buen dia");
				
				break;
			default:
				
				System.out.println("Opción no válida, escoja una de las opciones del menu");
				
				break;
			}
			
		}
		
	}

}
