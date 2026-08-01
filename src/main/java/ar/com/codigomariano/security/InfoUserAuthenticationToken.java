package ar.com.codigomariano.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class InfoUserAuthenticationToken extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = -1531682621825317742L;
	private String username;
	private Long idImagen;
	
	public InfoUserAuthenticationToken(Long id, String email,
			Collection<? extends GrantedAuthority> authorities) {
		super(id, email, authorities);
	}
	
	@Override
	public Long getPrincipal() {
		return (Long) super.getPrincipal();
	}
	
	@Override
	public String getCredentials() {
		return (String) super.getCredentials();
	}
	
	public Long getId() {
		return getPrincipal();
	}

	public String getEmail() {
		return getCredentials();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getIdImagen() {
		return idImagen;
	}
	public void setIdImagen(Long idImagen) {
		this.idImagen = idImagen;
	}
}
