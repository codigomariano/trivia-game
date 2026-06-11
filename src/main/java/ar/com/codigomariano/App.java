package ar.com.codigomariano;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ar.com.codigomariano.domain.Usuario;

public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String, Usuario> usuarios = new HashMap<String, Usuario>();
		
		System.out.println("==================");
		System.out.println("1. Alta de usuario");
		System.out.println("2. Salir");
		System.out.println("==================");
		
		System.out.print("> Seleccione una opción: ");
		int opcion = sc.nextInt();
		
		while(opcion != 2) {
			System.out.print("Ingrese un email: ");
			String email = sc.next();
			
			while(usuarios.containsKey(email.toLowerCase())) {
				System.out.println("El mail ingresado ya se encuentra registrado. Intente con otro!");
				System.out.println("> Ingrese un nuevo email: ");
				email = sc.next();
			}
			
			Usuario usuario = new Usuario(email);
			usuarios.put(email.toLowerCase(), usuario);
			System.out.print("== Usuario registrado! ==");
			
			System.out.println("");
			System.out.print("> Seleccione una opción: ");
			opcion = sc.nextInt();
		}

		System.out.println("La cantidad de usuarios registrados: " + usuarios.size());
		sc.close();
	}

}
