package ar.com.codigomariano.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.codigomariano.domain.Imagen;
import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.exceptions.MultipleUsersFoundException;
import ar.com.codigomariano.forms.DeletionForm;
import ar.com.codigomariano.forms.ProfileForm;
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
		Usuario user = new Usuario(email);
		
		this.repositorio.save(user);
	}
	
	@Override
	public void registrar(String email, String username, String fullName) {
		Usuario user = new Usuario(email, username);
		if(!ValidationUtils.isEmpty(fullName)) user.setNombreCompleto(fullName);
		
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
	public ProfileForm prepareProfile(String email) {
		ProfileForm form = new ProfileForm();
		
		if(!ValidationUtils.isEmpty(email)) {
			Usuario user = obtener(email, null);
			form.setId(user.getId());
			form.setEmail(user.getEmail());
			form.setUsername(user.getUsername());
			form.setFullName(user.getNombreCompleto());
			
			if(user.getImagen() != null) form.setIdPicture(user.getImagen().getId());
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
	public void actualizar(ProfileForm form) {
		Optional<Usuario> user = this.repositorio.findById(form.getId());
		if(user.isPresent()) {
			Usuario usuario = user.get();
			
			usuario.setEmail(form.getEmail());
			usuario.setUsername(form.getUsername());
			usuario.setNombreCompleto(form.getFullName());
			
			if(form.tieneImagenCargada()) {
				Imagen img;
				
				try {
					img = new Imagen(form.getFile().getOriginalFilename(), form.getFile().getContentType(), form.getFile().getBytes());
					usuario.setImagen(img);
				} catch (IOException e) {
					System.out.println("No se pudo asociar la imagen al usuario");
				}
			}
			
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
