package ar.com.codigomariano.services;

import ar.com.codigomariano.domain.Usuario;

public interface UsuarioService extends CRUDSevice<Usuario>{
	
	public void registrar(String email);
	
	public Usuario obtener(String email, Long id);
	
}
