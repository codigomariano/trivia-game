package ar.com.codigomariano.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.forms.UsuarioForm;
import ar.com.codigomariano.utilities.EntityHelper;


public class UsuarioServiceTest extends BaseServiceTest<Usuario, UsuarioForm, UsuarioService>{

	
	@Override
	protected Usuario crearEntidadValida() {
		return EntityHelper.createValidUser();
	}
	
	@Test
	@Transactional
	public void testGuardarAdministradorExitosamente() {
		String emailAdmin = EntityHelper.createRandomEmail();
		
		Usuario user = new Usuario(emailAdmin, EntityHelper.createRandomUsername(), true);
		
		servicio().guardar(user);
		
		Usuario admin = servicio().obtener(emailAdmin, null);
		
		assertTrue(admin.isAdmin());
	}
}
