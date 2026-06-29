package ar.com.codigomariano.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl extends CRUDServiceImpl<Usuario, UsuarioRepository> implements UsuarioService {
	@Autowired
	private UsuarioRepository repositorio;


	@Override
	public Usuario obtener(String email, Long id) {
		List<Usuario> usuarios = this.repositorio.findByEmailAndIdNot(email, id);
	
		if(usuarios == null || usuarios.isEmpty()) {
			// Lanzar una excepcion
		}else if(usuarios.size() > 1) {
			// Lanzar una excepcion
		}
		
		return usuarios.get(0);
	}
}
