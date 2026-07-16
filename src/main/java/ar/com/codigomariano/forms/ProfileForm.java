package ar.com.codigomariano.forms;

import org.springframework.web.multipart.MultipartFile;

import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.helpers.ValidationUtils;

public class ProfileForm extends EntityForm {
	private String email;
	private String username;
	private String fullName;
	private Long idPicture;
	private MultipartFile file;
	
	
	public boolean tieneImagenCargada() {
		return getFile() != null && getFile().getSize() > 0;
	}
	
	public boolean mostrarImagen() {
		return ValidationUtils.isValidID(this.idPicture);
	}
	
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Long getIdPicture() {
		return idPicture;
	}
	public void setIdPicture(Long idPicture) {
		this.idPicture = idPicture;
	}
}
