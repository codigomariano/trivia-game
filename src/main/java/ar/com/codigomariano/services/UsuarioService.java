package ar.com.codigomariano.services;

import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.forms.ProfileForm;
import ar.com.codigomariano.forms.UsuarioForm;

public interface UsuarioService extends CRUDService<Usuario, UsuarioForm>{
	
	public void registrar(String email);
	
	public void registrar(String email, String username, String fullName);
	
	public Usuario obtener(String email, Long id);
	
	public void actualizar(ProfileForm form);
	
	public ProfileForm prepareProfile(String email);

}
