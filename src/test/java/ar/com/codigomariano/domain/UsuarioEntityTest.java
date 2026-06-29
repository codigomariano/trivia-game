package ar.com.codigomariano.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ar.com.codigomariano.utilities.EntityHelper;

public class UsuarioEntityTest extends BaseEntityTest<Usuario>{

	
	@Override
	protected Usuario crearEntidadValida() {
		return EntityHelper.createValidUser();
	}

	@Override
	protected void validarEntidadExitosa(Usuario entidad) {
		assertEquals(EntityHelper.VALID_EMAIL, entidad.getEmail());
		assertNotNull(entidad.getUsername());
		assertNotNull(entidad.getFechaCreacion());
	}
	

	@Test
	public void testCrearUsuarioConEmailNulo() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Usuario(null));
		
		assertEquals(Usuario.ERR_EMAIL_OBLIGATORIO, ex.getMessage());
	}
	
	@Test
	public void testCrearUsuarioConEmailVacio() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Usuario(" "));
		
		assertEquals(Usuario.ERR_EMAIL_OBLIGATORIO, ex.getMessage());
	}
	
	@Test
	public void testCrearUsuarioConEmailInvalido() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Usuario("no_valid"));
		
		assertEquals(Usuario.ERR_EMAIL_INVALID, ex.getMessage());
	}
	
	@Test
	public void testCrearUsuarioConUsernameNulo() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Usuario(EntityHelper.createRandomEmail(), null));
		
		assertEquals(Usuario.ERR_USERNAME_OBLIGATORIO, ex.getMessage());
	}
	
	@Test
	public void testCrearUsuarioConUsernameNVacio() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Usuario(EntityHelper.createRandomEmail(), ""));
		
		assertEquals(Usuario.ERR_USERNAME_OBLIGATORIO, ex.getMessage());
	}
}
