package ar.com.codigomariano.session;

import ar.com.codigomariano.helpers.ValidationUtils;

public class InfoSession {
	private String email;
	private Integer puntos;
	
	
	public InfoSession(String email) {
		this.email = email;
		this.puntos = 0;
	}

	public boolean isValidEmail() {
		return !ValidationUtils.isEmpty(this.email);
	}
	
	public String getEmail() {
		return email;
	}
	public Integer getPuntos() {
		return puntos;
	}
}
