package ar.com.codigomariano.helpers;

public class ValidationUtils {

	
	/**
	 * Verifica si el texto ingresado contiene el carácter '@',
	 * utilizado como criterio básico para identificar un correo electrónico.il
	 */
	public static boolean isValidEmail(String email) {
		boolean resultado = false;
		
		if(email != null && !email.isBlank()) {
			resultado = email.contains("@");
		}
		
		return resultado;
	}
	
	
	/**
	 * Verifica que un texto no esté vacío.
	 * Se considera vacío cuando el valor es nulo o cuando
	 * contiene únicamente espacios en blanco.
	 */
	public static boolean isEmpty(String texto) {
		return texto == null || texto.isBlank();
	}
	
	
	/**
	 * Verifica que la longitud del texto sea mayor que la cantidad
	 * de caracteres indicada.
	 *
	 * La comparación se realiza utilizando un criterio de mayor exclusivo.
	 */
	public static boolean tieneMasDe(String texto, int cantidad) {
		boolean resultado = false;
		
		if(texto != null && !texto.isBlank()) {
			resultado = texto.length() > cantidad;
		}
		
		return resultado;
	}

	
	/**
	 * Verifica que la longitud del texto sea menor que la cantidad
	 * de caracteres indicada.
	 *
	 * La comparación se realiza utilizando un criterio de menor exclusivo.
	 */
	public static boolean tieneMenosDe(String texto, int cantidad) {
		boolean resultado = false;
		
		if(texto != null && !texto.isBlank()) {
			resultado = texto.length() < cantidad;
		}
		
		return resultado;
	}
}
