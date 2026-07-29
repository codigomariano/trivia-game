package ar.com.codigomariano.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioDTO implements DTO{
	private String email;
	@JsonProperty(value = "fullName")
	private String nombreCompleto;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
}
