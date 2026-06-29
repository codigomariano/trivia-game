package ar.com.codigomariano.helpers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.com.codigomariano.domain.Usuario;

public class UtilsTest {

	@Test
	public void testGenerarUsernameValido() {
		String username = Utils.generarUsername();
		
		assertNotNull(username);
		assertTrue(username.startsWith(Utils.DEFAULT_USERNAME_PREFIX));
		assertTrue(ValidationUtils.tieneMasDe(username, Usuario.USERNAME_MIN_LENGTH));
		assertFalse(ValidationUtils.tieneMasDe(username, Usuario.USERNAME_MAX_LENGTH));
	}
}
