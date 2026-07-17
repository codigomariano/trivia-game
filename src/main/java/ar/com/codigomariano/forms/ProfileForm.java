package ar.com.codigomariano.forms;

import org.springframework.web.multipart.MultipartFile;

import ar.com.codigomariano.helpers.ValidationUtils;

public class ProfileForm extends UsuarioForm {
	private Long idPicture;
	private MultipartFile file;
	
	
	public boolean tieneImagenCargada() {
		return getFile() != null && getFile().getSize() > 0;
	}
	
	public boolean mostrarImagen() {
		return ValidationUtils.isValidID(this.idPicture);
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
