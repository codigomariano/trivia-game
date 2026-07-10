package ar.com.codigomariano.forms;

import ar.com.codigomariano.domain.Usuario;

public class UsuarioForm extends EntityForm{
	private String email;
	private String username;
	private String fullName;
	
	
	public int getEmailMaxLength() {
		return Usuario.EMAIL_MAX_LENGTH;
	}

	public int getUsernameMinLength() {
		return Usuario.USERNAME_MIN_LENGTH;
	}
	
	public int getUsernameMaxLength() {
		return Usuario.USERNAME_MAX_LENGTH;
	}
	
	public int getFullNameMaxLength() {
		return Usuario.FULL_NAME_MAX_LENGTH;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
