package ar.com.codigomariano.domain;

import ar.com.codigomariano.enums.OpcionMultiple;
import ar.com.codigomariano.helpers.ValidationUtils;

public class Respuesta extends Persistible{
	public static final String ERR_OPCION_OBLIGATORIO = "La opción es obligatoria";
	public static final String ERR_TEXTO_OBLIGATORIO = "El texto es obligatorio";
	public static final String ERR_TEXTO_LENGTH = "El texto puede superar los %d caracteres";

	public static final int TEXTO_MAX_LENGTH = 250;
	
	private OpcionMultiple opcion;
	private String texto;
	private Boolean correcta;
	
	
	public Respuesta(OpcionMultiple opcion, String texto) {
		this(opcion, texto, Boolean.FALSE);
	}
	
	public Respuesta(OpcionMultiple opcion, String texto, Boolean esCorrecta) {
		setOpcion(opcion);
		setTexto(texto);
		setCorrecta(esCorrecta);
	}

	
	public OpcionMultiple getOpcion() {
		return opcion;
	}
	
	public String getTexto() {
		return texto;
	}

	public Boolean getCorrecta() {
		return correcta;
	}

	public void setOpcion(OpcionMultiple opcion) {
		if(opcion == null) throw new IllegalArgumentException(ERR_OPCION_OBLIGATORIO);
		this.opcion = opcion;
	}
	
	public void setTexto(String texto) {
		if(ValidationUtils.isEmpty(texto)) {
			throw new IllegalArgumentException(ERR_TEXTO_OBLIGATORIO);
		}else if(ValidationUtils.tieneMasDe(texto, TEXTO_MAX_LENGTH)) {
			throw new IllegalArgumentException(String.format(ERR_TEXTO_LENGTH, TEXTO_MAX_LENGTH));
		}
		this.texto = texto;
	}

	public void setCorrecta(Boolean correcta) {
		this.correcta = correcta;
	}
}
