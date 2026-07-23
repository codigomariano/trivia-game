package ar.com.codigomariano.dtos;

public class UsuarioDTO implements DTO{
	private String email;
	private String fullName;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
