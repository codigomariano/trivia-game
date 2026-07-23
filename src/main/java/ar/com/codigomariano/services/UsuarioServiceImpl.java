package ar.com.codigomariano.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.codigomariano.domain.Imagen;
import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.dtos.UsuarioDTO;
import ar.com.codigomariano.exceptions.MultipleUsersFoundException;
import ar.com.codigomariano.forms.ProfileForm;
import ar.com.codigomariano.forms.UsuarioForm;
import ar.com.codigomariano.helpers.ValidationUtils;
import ar.com.codigomariano.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl extends ExponibleServiceImpl<Usuario, UsuarioForm, UsuarioRepository, UsuarioDTO> implements UsuarioService {
	@Autowired
	private UsuarioRepository repositorio;

	
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
		
		guardar(user);
	}
	
	@Override
	public void registrar(String email, String username, String fullName) {
		Usuario user = new Usuario(email, username);
		if(!ValidationUtils.isEmpty(fullName)) user.setNombreCompleto(fullName);
		
		guardar(user);
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
	public void actualizar(ProfileForm form) {
		Usuario usuario = obtenerYactualizarUsuario(form);

		if(usuario != null && form.tieneImagenCargada()) {
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

	
	@Override
	protected UsuarioForm emptyForm() {
		return new UsuarioForm();
	}


	/**
	 * Actualiza la entidad de negocio utilizando la información proveniente del
	 * formulario recibido. Este método puede operar tanto con un formulario de
	 * administración de usuarios ({@code UsuarioForm}) como con el formulario de
	 * edición del perfil del usuario autenticado ({@code ProfileForm}).
	 */
	private Usuario obtenerYactualizarUsuario(UsuarioForm form) {
		Usuario usuario = null;
		Optional<Usuario> user = this.repositorio.findById(form.getId());
		
		if(user.isPresent()) {
			usuario = user.get();
			user.get().updatePropertiesFromForm(form);
		}
		
		return usuario;
	}

}
