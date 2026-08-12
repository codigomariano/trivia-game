package ar.com.codigomariano.helpers;

import java.util.Collection;
import java.util.Random;

import ar.com.codigomariano.enums.PreguntaType;

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
	
	
	/**
	 * Obtiene un tipo de pregunta de forma aleatoria
	 */
	public static PreguntaType obtenerTipoPregunta() {
		int index = random.nextInt(0, PreguntaType.values().length);
		
		return PreguntaType.values()[index];
	}
	
	
	/**
	 * Obtiene un índice válido para seleccionar un elemento
	 * de una colección
	 */
	public static int obtenerIndice(Collection<?> elementos) {
		int index = -1;
		if(elementos != null && !elementos.isEmpty()) index = random.nextInt(index, elementos.size());
		
		return index;
	}
}
