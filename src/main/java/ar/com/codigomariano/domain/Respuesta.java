package ar.com.codigomariano.domain;

import ar.com.codigomariano.enums.OpcionMultiple;

public class Respuesta extends Persistible{
	private OpcionMultiple opcion;
	private String texto;
	private Boolean correcta;
	
	
	public Respuesta(OpcionMultiple opcion, String texto) {
		this(opcion, texto, Boolean.FALSE);
	}
	
	public Respuesta(OpcionMultiple opcion, String texto, Boolean esCorrecta) {
		this.opcion = opcion;
		this.texto = texto;
		this.correcta = esCorrecta;
	}
}
