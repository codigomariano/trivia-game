package ar.com.codigomariano.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.exceptions.UsuarioNotFoundException;


@Service
public class JWTServiceImpl implements JWTService {
	private final String KEY_EMAIL = "email";
	private final String KEY_AUTHO = "authorization";
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@Override
	public String login(String email) {
		String token = null;
		Usuario user = this.usuarioService.obtener(email, null);
		
		if(user != null) {
			token = buildToken(user);
		} else throw new UsuarioNotFoundException(email);
		
		
		return token;
	}

	
	private String buildToken(Usuario user) {
		return JWT.create()
				.withKeyId("my-app-"+user.getId()+"-"+System.currentTimeMillis())
				.withExpiresAt(Instant.now().plusSeconds(300))
				.withClaim(KEY_EMAIL, user.getEmail())
				.withClaim(KEY_AUTHO, prepararPermisos(user))
				.sign(Algorithm.HMAC512("my-password-secreta"));
	}
	
	
	private List<String> prepararPermisos(Usuario user) {
		
		return user.collectAuthorities()
				.stream()
				.map(p -> p.toString())
				.collect(Collectors.toList());
	}
}
