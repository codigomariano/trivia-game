package ar.com.codigomariano.domain;

import java.time.LocalDateTime;
import java.util.Random;

public class Usuario extends Persistible {
	public static final String DEFAULT_USERNAME_PREFIX = "user";
	private LocalDateTime fechaCreacion;
	private String username;
	private String email;
	private String nombreCompleto;
	private Imagen imagen;
	
	
	public Usuario(String email) {
		this(generateDefaultUsername(), email);
	}
	
	public Usuario(String username, String email) {
		this.fechaCreacion = LocalDateTime.now();
		this.username = username;
		this.email = email;
	}
	
	
	private static String generateDefaultUsername() {
		Random r = new Random(System.currentTimeMillis());
		return DEFAULT_USERNAME_PREFIX + r.nextInt();
	}
}
