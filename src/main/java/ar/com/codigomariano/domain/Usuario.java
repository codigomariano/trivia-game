package ar.com.codigomariano.domain;

import java.time.LocalDateTime;

import ar.com.codigomariano.helpers.Utils;
import ar.com.codigomariano.helpers.ValidationUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class Usuario extends Persistible {
	public static final String ERR_EMAIL_OBLIGATORIO = "El email es obligatorio";
	public static final String ERR_EMAIL_MAX_LENGTH = "El email no puede superar los %d caracteres";
	public static final String ERR_EMAIL_INVALID = "El email no tiene un formato válido";
	public static final String ERR_USERNAME_OBLIGATORIO = "El username es obligatorio";
	public static final String ERR_USERNAME_LENGTH = "El username debe tener entre %d y %d caracteres";
	public static final String ERR_FULLNAME_MAX_LENGTH = "El nombre completo no puede superar los %d caracteres";

	public static final int EMAIL_MAX_LENGTH = 75;
	public static final int USERNAME_MIN_LENGTH = 4;
	public static final int USERNAME_MAX_LENGTH = 50;
	public static final int FULL_NAME_MAX_LENGTH = 100;
	
	@Column(name = "FECHA_CREACION")
	private LocalDateTime fechaCreacion;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "FULL_NAME")
	private String nombreCompleto;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "IMAGEN_ID", nullable = true)
	private Imagen imagen;
	
	
	// Sólo para Hibernate
	Usuario() {
		super();
	}
	
	public Usuario(String email) {
		this(email, Utils.generarUsername());
	}
	
	public Usuario(String email, String username) {
		this.fechaCreacion = LocalDateTime.now();
		setUsername(username);
		setEmail(email);
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public Imagen getImagen() {
		return imagen;
	}
		
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	
	
	public void setEmail(String email) {
		if(ValidationUtils.isEmpty(email)) {
			throw new IllegalArgumentException(ERR_EMAIL_OBLIGATORIO);
		} else if(ValidationUtils.tieneMasDe(email, EMAIL_MAX_LENGTH)) {
			throw new IllegalArgumentException(String.format(ERR_EMAIL_MAX_LENGTH, EMAIL_MAX_LENGTH));
		} else if (!ValidationUtils.isValidEmail(email)) {
			throw new IllegalArgumentException(ERR_EMAIL_INVALID);
		}
		this.email = email;
	}

	public void setUsername(String username) {
		if(ValidationUtils.isEmpty(username)) {
			throw new IllegalArgumentException(ERR_USERNAME_OBLIGATORIO);
		} else if (ValidationUtils.tieneMenosDe(username, USERNAME_MIN_LENGTH) ||
				ValidationUtils.tieneMasDe(username, USERNAME_MAX_LENGTH)) {
			throw new IllegalArgumentException(String.format(ERR_USERNAME_LENGTH, USERNAME_MIN_LENGTH, USERNAME_MAX_LENGTH));
		}
		this.username = username;
	}


	public void setNombreCompleto(String nombreCompleto) {
		if(!ValidationUtils.isEmpty(nombreCompleto) && ValidationUtils.tieneMasDe(nombreCompleto, FULL_NAME_MAX_LENGTH)) {
			throw new IllegalArgumentException(String.format(ERR_FULLNAME_MAX_LENGTH, FULL_NAME_MAX_LENGTH));
		}
		this.nombreCompleto = nombreCompleto;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}
}
