package ar.com.codigomariano;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ar.com.codigomariano.domain.Usuario;

public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String, Usuario> usuarios = new HashMap<String, Usuario>();
		
		int opcion = solicitarOpcionDeMenu(sc);
		
		while(opcion != 2) {
			String email = leerOpcion(sc, "Ingrese un email");
			
			while(usuarios.containsKey(email.toLowerCase())) {
				System.out.println("El mail ingresado ya se encuentra registrado. Intente con otro!");
				email = leerOpcion(sc, "Ingres un nuevo email");
			}
			
			Usuario usuario = new Usuario(email);
			usuarios.put(email.toLowerCase(), usuario);
			System.out.print("== Usuario registrado! ==");
			
			opcion = solicitarOpcionDeMenu(sc);
		}

		System.out.println("La cantidad de usuarios registrados: " + usuarios.size());
		sc.close();
	}

	
	private static int solicitarOpcionDeMenu(Scanner sc) {
		mostrarMenu();
		int opcion = leerOpcion(sc);
		
		return opcion;
	}
	
	private static void mostrarMenu() {
		System.out.println("==================");
		System.out.println("1. Alta de usuario");
		System.out.println("2. Salir");
		System.out.println("==================");
	}
	
	private static int leerOpcion(Scanner sc) {
		System.out.println("");
		System.out.print("> Seleccione una opción: ");
		int opcion = sc.nextInt();
		
		return opcion;
	}
	
	private static String leerOpcion(Scanner sc, String texto) {
		System.out.println("");
		System.out.print("> " + texto + ":");
		String opcion = sc.next();
		
		return opcion;
	}
}
