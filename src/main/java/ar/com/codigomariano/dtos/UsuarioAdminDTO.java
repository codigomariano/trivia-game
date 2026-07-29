package ar.com.codigomariano.dtos;

import java.time.LocalDateTime;

public class UsuarioAdminDTO extends UsuarioDTO {
	private Long id;
	private LocalDateTime fechaCreacion;
	private String username;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
