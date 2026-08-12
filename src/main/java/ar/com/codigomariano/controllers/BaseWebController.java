package ar.com.codigomariano.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import ar.com.codigomariano.security.InfoUserAuthenticationToken;

public abstract class BaseWebController {
	public static final String ROOT_URL = "/";
	public static final String BASE_URL = "/web";
	protected final static String ID_PARAMETER = "id";
	
	protected final static String PARTIDA = "partida";
	protected final static String FORM_ATTRIBUTE = "form";
	protected final static String LIST_ATTRIBUTE = "listado";

	
	/**
	 * Obtiene la información de la sesión asociada al usuario autenticado
	 */
	public InfoUserAuthenticationToken authenticationToken() {
		SecurityContext context = SecurityContextHolder.getContext();
		
		Authentication auth = null;
		if(context != null && context.getAuthentication() != null) {
			auth = context.getAuthentication();
		}
		
		return (InfoUserAuthenticationToken) auth;
	}
	
	
	/**
	 * Determina si existe un usuario autenticado verificando 
	 * si el contexto de seguridad contiene un token de seguridad
	 * asociado al usuario.
	 */
	public boolean estaUsuarioLogueado() {
		Authentication auth = authenticationToken();
		return auth != null;
	}
	
	
	/**
	 * Realiza una redirección a la URL indicada
	 * por parámetro
	 */
	protected String redirect(String path) {
		return "redirect:"+path;
	}
}
