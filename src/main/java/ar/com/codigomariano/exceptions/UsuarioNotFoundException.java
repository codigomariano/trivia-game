package ar.com.codigomariano.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -4807165696956201981L;
	private static final String ERR_MSG = "No se encontraron usuarios con el siguiente mail registrado [%s]";


	public UsuarioNotFoundException(String email) {
		super(String.format(ERR_MSG, email));
	}
}
