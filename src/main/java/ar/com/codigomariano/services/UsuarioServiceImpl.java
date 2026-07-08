package ar.com.codigomariano.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.exceptions.MultipleUsersFoundException;
import ar.com.codigomariano.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl extends CRUDServiceImpl<Usuario, UsuarioRepository> implements UsuarioService {
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
		
		this.repositorio.save(user);
	}
	
}
