package ar.com.codigomariano.forms;

import ar.com.codigomariano.domain.Usuario;

public class LoginForm {
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getEmailMaxLength() {
		return Usuario.EMAIL_MAX_LENGTH;
	}
}
