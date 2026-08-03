package ar.com.codigomariano.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ar.com.codigomariano.dtos.DTO;
import ar.com.codigomariano.dtos.UsuarioAdminDTO;
import ar.com.codigomariano.dtos.UsuarioDTO;
import ar.com.codigomariano.enums.Rol;
import ar.com.codigomariano.forms.UsuarioForm;
import ar.com.codigomariano.helpers.Utils;
import ar.com.codigomariano.helpers.ValidationUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class Usuario extends Exponible<UsuarioForm> {
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
	
	@Enumerated(EnumType.ORDINAL)
	@ElementCollection(targetClass = Rol.class)
	@CollectionTable(name = "ROLES_USUARIOS", joinColumns = @JoinColumn(name = "USUARIO_ID"))
	@Column(name = "ROL_ID")
	private List<Rol> roles;
	
	
	// Sólo para Hibernate
	Usuario() {
		super();
	}
	
	public Usuario(String email) {
		this(email, Utils.generarUsername());
	}
	
	public Usuario(String email, String username) {
		this(email, username, false);
	}
	
	public Usuario(String email, String username, boolean isAdmin) {
		this.fechaCreacion = LocalDateTime.now();
		this.roles = new ArrayList<Rol>();
		setUsername(username);
		setEmail(email);
		this.roles.add(Rol.JUGADOR);
		if(isAdmin) this.roles.add(Rol.ADMINISTRADOR);
	}

	
	@Override
	public void copyPropertiesToForm(UsuarioForm form) {
		form.setId(getId());
		form.setEmail(getEmail());
		form.setUsername(getUsername());
		form.setFullName(getNombreCompleto());
	}

	@Override
	public void updatePropertiesFromForm(UsuarioForm form) {
		setEmail(form.getEmail());
		setUsername(form.getUsername());
		setNombreCompleto(form.getFullName());
	}
	
	@Override
	public Class<? extends DTO> dtoForList() {
		return UsuarioAdminDTO.class;
	}

	@Override
	public Class<? extends DTO> dtoForAPI() {
		return UsuarioDTO.class;
	}
		
	
	public List<GrantedAuthority> collectAuthorities() {
		List<GrantedAuthority> credentials = new ArrayList<GrantedAuthority>();
		
		for (Rol rol : this.roles) {
			credentials.add(new SimpleGrantedAuthority(rol.getSecurityName()));
			credentials.addAll(rol.getSecurityPermissions());
		}
		
		return credentials;
	}
	
	// SETTER & GETTERS
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
	
	public boolean isAdmin() {
		return this.roles.contains(Rol.ADMINISTRADOR);
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
