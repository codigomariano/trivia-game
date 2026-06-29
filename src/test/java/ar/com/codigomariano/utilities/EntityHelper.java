package ar.com.codigomariano.utilities;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.enums.OpcionMultiple;

public class EntityHelper {
	private static Random random = new Random(System.currentTimeMillis());
	private static final String ERR_READING_FILE = "El archivo solicitado [%s] no se pudo leer";
	
	public static final String EMAIL_PREFIX = "mail_";
	public static final String VALID_EMAIL = "mariano@codigo.com.ar";
	public static final String VALID_PROFILE_IMG_NAME = "profile.png";
	public static final String EXISTING_PROFILE_IMG = "imagenes/profile.png";
	
	
	/**
	 * Crea un usuario que cumple todas las reglas de validación de la entidad
	 */
	public static Usuario createValidUser() {
		return new Usuario(VALID_EMAIL);
	}
	
	
	/**
	 * Genera un correo electrónico válido con un valor aleatorio 
	 * para evitar duplicados en las pruebas
	 */
	public static String createRandomEmail() {
		return EMAIL_PREFIX + random.nextInt() + "@dummy.com.ar";
	}
	

	/**
	 * Obtiene una opción múltiple aleatoria para su utilización en pruebas
	 */
	public static OpcionMultiple getRandomOpcionMultiple() {
		int index = random.nextInt(OpcionMultiple.values().length);
		return OpcionMultiple.values()[index];
	}
	
	
	/**
	 * Obtiene el contenido de un archivo ubicado en el classpath
	 */
	public static byte[] readFileFromResource(String file) {
		InputStream stream = EntityHelper.class.getClassLoader().getResourceAsStream(file);
		byte[] contenido = null;
		
		try {
			contenido = stream.readAllBytes();
		} catch (IOException e) {
			fail(String.format(ERR_READING_FILE, file));
		}
		
		return contenido;
	}
}
