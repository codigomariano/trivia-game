package ar.com.codigomariano.services;

import java.util.List;

import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.forms.DeletionForm;
import ar.com.codigomariano.forms.ProfileForm;
import ar.com.codigomariano.forms.UsuarioForm;

public interface UsuarioService extends CRUDSevice<Usuario>{
	
	public List<Usuario> listAll();
	
	public void registrar(String email);
	
	public void registrar(String email, String username, String fullName);
	
	public Usuario obtener(String email, Long id);
		
	public void actualizar(UsuarioForm form);
	
	public void actualizar(ProfileForm form);
	
	public void eliminar(DeletionForm form);
	
	public UsuarioForm prepareEntity(Long id);
	
	public ProfileForm prepareProfile(String email);

}
