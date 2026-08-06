package ar.com.codigomariano.services;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.exceptions.UsuarioNotFoundException;
import ar.com.codigomariano.security.InfoUserAuthenticationToken;


@Service
public class JWTServiceImpl implements JWTService {
	private final String KEY_ID = "id";
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

	
	@Override
	public void buildAuthenticationContext(String token) {
		DecodedJWT decoredToken = JWT.decode(token);
		
		Long id = decoredToken.getClaim(KEY_ID).asLong();
		String email = decoredToken.getClaim(KEY_EMAIL).asString();
		List<String> permisos = decoredToken.getClaim(KEY_AUTHO).asList(String.class);
		
		InfoUserAuthenticationToken authorization = new InfoUserAuthenticationToken(id, email, prepararPermisos(permisos));
	
		SecurityContext context = SecurityContextHolder.getContext();
		
		context.setAuthentication(authorization);
	}
	
	/**
	 * Genera un token JWT utilizando la información del usuario
	 * recibida como parámetro.
	 */
	private String buildToken(Usuario user) {
		return JWT.create()
				.withKeyId("my-app-"+user.getId()+"-"+System.currentTimeMillis())
				.withExpiresAt(Instant.now().plusSeconds(300))
				.withClaim(KEY_ID, user.getId())
				.withClaim(KEY_EMAIL, user.getEmail())
				.withClaim(KEY_AUTHO, prepararPermisos(user))
				.sign(Algorithm.HMAC512("my-password-secreta"));
	}
	
	/**
	* Convierte los roles y permisos del usuario en una colección
	* de cadenas de texto para incluirlos dentro del token JWT.
	 */
	private List<String> prepararPermisos(Usuario user) {
		
		return user.collectAuthorities()
				.stream()
				.map(p -> p.toString())
				.collect(Collectors.toList());
	}
	
	/**
	* Convierte los roles y permisos extraídos de un token JWT
	* en formato de texto para transformarlos en una colección
	* de objetos GrantedAuthority
	 */
	private Collection<GrantedAuthority> prepararPermisos(List<String> permisos) {
		
		return permisos
				.stream()
				.map(p -> new SimpleGrantedAuthority(p))
				.collect(Collectors.toList());
		
	}
}
