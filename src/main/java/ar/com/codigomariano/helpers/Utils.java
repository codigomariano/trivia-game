package ar.com.codigomariano.helpers;

import java.util.Random;

public class Utils {
	public static final String DEFAULT_USERNAME_PREFIX = "user";
	private static Random random = new Random(System.currentTimeMillis());
	
	
	/**
	 * Genera un nombre de usuario de forma aleatoria utilizando el prefijo
	 * "user" seguido de un número entero generado aleatoriamente.
	 */
	public static String generarUsername() {
		return DEFAULT_USERNAME_PREFIX + random.nextInt();
	}
}
