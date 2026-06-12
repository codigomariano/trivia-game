package ar.com.codigomariano.domain;

import ar.com.codigomariano.helpers.ValidationUtils;

public class Imagen extends Persistible {
	public static final String ERR_NOMBRE_OBLIGATORIO = "El nombre de la imagen es obligatorio";
	public static final String ERR_NOMBRE_MAX_LENGTH = "El nombre de la imagen no puede superar los %d caracteres";
	public static final String ERR_CONTENIDO_OBLIGATORIO = "El contenido de la imagen es obligatorio";

	public static final int NOMBRE_MAX_LENGTH = 100;
	
	private String nombre;
	private String contentType;
	private byte[] contenido;
	
	
	public Imagen(String nombre, byte[] contenido) {
		this(nombre, null, contenido);
	}
	
	public Imagen(String nombre, String contentType, byte[] contenido) {
		setNombre(nombre);
		setContentType(contentType);
		setContenido(contenido);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(ValidationUtils.isEmpty(nombre)) {
			throw new IllegalArgumentException(ERR_NOMBRE_OBLIGATORIO);
		}else if(ValidationUtils.tieneMasDe(nombre, NOMBRE_MAX_LENGTH)) {
			throw new IllegalArgumentException(String.format(nombre, NOMBRE_MAX_LENGTH));
		}
		this.nombre = nombre;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getContenido() {
		return contenido;
	}

	public void setContenido(byte[] contenido) {
		if(contenido == null) throw new IllegalArgumentException(ERR_CONTENIDO_OBLIGATORIO);
		this.contenido = contenido;
	}
}
