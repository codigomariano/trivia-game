package ar.com.codigomariano.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import ar.com.codigomariano.domain.Usuario;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario>{

	List<Usuario> findByEmailAndIdNot(String email, Long id);

}
