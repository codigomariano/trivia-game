package ar.com.codigomariano.controllers;

public abstract class BaseController {
	protected final static String FORM_ATTRIBUTE = "form";

	
	/**
	 * Realiza una redirección a la URL indicada
	 * por parámetro
	 */
	protected String redirect(String path) {
		return "redirect:"+path;
	}
}
