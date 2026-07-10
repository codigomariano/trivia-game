package ar.com.codigomariano.controllers;

public abstract class BaseController {
	protected final static String ID_PARAMETER = "id";
	protected final static String FORM_ATTRIBUTE = "form";
	protected final static String LIST_ATTRIBUTE = "listado";

	
	/**
	 * Realiza una redirección a la URL indicada
	 * por parámetro
	 */
	protected String redirect(String path) {
		return "redirect:"+path;
	}
}
