package ar.com.codigomariano.forms;

import ar.com.codigomariano.enums.OpcionMultiple;

public class RespuestaForm extends EntityForm {
	private OpcionMultiple opcion;
	private String texto;
	private Boolean correcta;
	
	
	public RespuestaForm() {
		super();
	}
	
	public RespuestaForm(OpcionMultiple opcion) {
		super();
		this.opcion = opcion;
		this.correcta = Boolean.FALSE;
	}
	
	public RespuestaForm(OpcionMultiple opcion, boolean esCorrecta) {
		super();
		this.opcion = opcion;
		this.correcta = esCorrecta;
	}
	
	public OpcionMultiple getOpcion() {
		return opcion;
	}
	public void setOpcion(OpcionMultiple opcion) {
		this.opcion = opcion;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Boolean getCorrecta() {
		return correcta;
	}
	public void setCorrecta(Boolean correcta) {
		this.correcta = correcta;
	}
}
