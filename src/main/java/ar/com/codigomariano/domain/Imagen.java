package ar.com.codigomariano.domain;

public class Imagen extends Persistible {
	private String nombre;
	private String contentType;
	private byte[] contenido;
	
	
	public Imagen(String nombre, byte[] contenido) {
		this(nombre, null, contenido);
	}
	
	public Imagen(String nombre, String contentType, byte[] contenido) {
		this.nombre = nombre;
		this.contentType = contentType;
		this.contenido = contenido;
	}
}
