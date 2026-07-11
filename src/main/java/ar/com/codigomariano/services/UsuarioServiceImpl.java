package ar.com.codigomariano.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.exceptions.MultipleUsersFoundException;
import ar.com.codigomariano.forms.DeletionForm;
import ar.com.codigomariano.forms.UsuarioForm;
import ar.com.codigomariano.helpers.ValidationUtils;
import ar.com.codigomariano.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl extends CRUDServiceImpl<Usuario, UsuarioRepository> implements UsuarioService {
	@Autowired
	private UsuarioRepository repositorio;


	@Override
	public List<Usuario> listAll() {
		return this.repositorio.findAll();
	}
	
	@Override
	public Usuario obtener(String email, Long id) {
		List<Usuario> usuarios = this.repositorio.findByEmailAndIdNot(email, id);
	
		Usuario encontrado = null;
		if(usuarios != null && !usuarios.isEmpty()) {
			if(usuarios.size() == 1) {
				encontrado = usuarios.get(0);
			} else new MultipleUsersFoundException(email);
		}
		
		return encontrado;
	}
	
	@Override
	public void registrar(String email) {
		registrar(email, null, null);
	}
	
	@Override
	public void registrar(String email, String username, String fullName) {
		Usuario user = new Usuario(email, username);
		user.setNombreCompleto(fullName);
		
		this.repositorio.save(user);
	}
	
	@Override
	public UsuarioForm prepareEntity(Long id) {
		UsuarioForm form = new UsuarioForm();
		
		if(ValidationUtils.isValidID(id)) {
			Optional<Usuario> user = this.repositorio.findById(id);
			if(user.isPresent()) user.get().copyPropertiesToForm(form);
		}
		
		return form;
	}
	
	@Override
	public void actualizar(UsuarioForm form) {
		Optional<Usuario> user = this.repositorio.findById(form.getId());
		if(user.isPresent()) {
			Usuario usuario = user.get();
			
			user.get().updatePropertiesFromForm(form);
			guardar(usuario);
		}
	}
	
	@Override
	public void eliminar(DeletionForm form) {
		if(ValidationUtils.isValidID(form.getId())) {
			this.repositorio.deleteById(form.getId());
		}
	}
}
