package ar.com.codigomariano.services;

import ar.com.codigomariano.domain.Usuario;

public interface UsuarioService {

	public void guardar(Usuario user);
	
	public Usuario obtener(String email, Long id);
	
}
