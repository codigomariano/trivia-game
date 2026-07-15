package ar.com.codigomariano.controllers;

import ar.com.codigomariano.session.InfoSession;
import jakarta.servlet.http.HttpSession;

public abstract class BaseController {
	protected final static String ID_PARAMETER = "id";
	
	protected final static String INFO_ATTRIBUTE = "info";
	protected final static String FORM_ATTRIBUTE = "form";
	protected final static String LIST_ATTRIBUTE = "listado";

	
	/**
	 * Obtiene la información de la sesión asociada al usuario autenticado
	 */
	public InfoSession getInfoSession(HttpSession session) {
		return (InfoSession) session.getAttribute(INFO_ATTRIBUTE);
	}
	
	
	/**
	 * Determina si existe un usuario autenticado verificando 
	 * si la sesión HTTP contiene un objeto de sesión asociado al usuario.
	 * @param session
	 * @return
	 */
	public boolean estaUsuarioLogueado(HttpSession session) {
		InfoSession info = getInfoSession(session);
		return info != null && info.isValidEmail();
	}
	
	
	/**
	 * Realiza una redirección a la URL indicada
	 * por parámetro
	 */
	protected String redirect(String path) {
		return "redirect:"+path;
	}
}
