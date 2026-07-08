package ar.com.codigomariano.exceptions;

public class MultipleUsersFoundException extends RuntimeException {
	private static final long serialVersionUID = -2342507151301494341L;
	private static final String ERR_MSG = "Se detectaron más de un usuario registrados con el email [%s]";


	public MultipleUsersFoundException(String email) {
		super(String.format(ERR_MSG, email));
	}
}
