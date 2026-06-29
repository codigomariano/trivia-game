package ar.com.codigomariano.services;

import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.utilities.EntityHelper;


public class UsuarioServiceTest extends BaseServiceTest<Usuario, UsuarioService>{

	
	@Override
	protected Usuario crearEntidadValida() {
		return EntityHelper.createValidUser();
	}
}
