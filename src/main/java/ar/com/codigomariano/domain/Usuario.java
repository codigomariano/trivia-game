package ar.com.codigomariano.domain;

import java.time.LocalDateTime;
import java.util.Random;

import ar.com.codigomariano.helpers.Utils;

public class Usuario extends Persistible {
	private LocalDateTime fechaCreacion;
	private String username;
	private String email;
	private String nombreCompleto;
	private Imagen imagen;
	
	
	public Usuario(String email) {
		this(Utils.generarUsername(), email);
	}
	
	public Usuario(String username, String email) {
		this.fechaCreacion = LocalDateTime.now();
		this.username = username;
		this.email = email;
	}
}
