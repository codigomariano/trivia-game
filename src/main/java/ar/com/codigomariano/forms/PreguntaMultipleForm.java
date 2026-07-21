package ar.com.codigomariano.forms;

import ar.com.codigomariano.enums.Opcion;
import ar.com.codigomariano.enums.OpcionMultiple;
import ar.com.codigomariano.enums.PreguntaType;

public class PreguntaMultipleForm extends PreguntaForm {
	private RespuestaForm[] respuestas;
	
	
	public PreguntaMultipleForm() {
		super(PreguntaType.MULTIPLE);
		inicializarFormularioRespuestas();
	}
	
	@Override
	public Opcion[] getOpciones() {
		return OpcionMultiple.values();
	}

	public RespuestaForm[] getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(RespuestaForm[] respuestas) {
		this.respuestas = respuestas;
	}
	
	private void inicializarFormularioRespuestas() {
		this.respuestas = new RespuestaForm[OpcionMultiple.values().length];
		
		for (OpcionMultiple opcion : OpcionMultiple.values()) {
			this.respuestas[opcion.ordinal()] = new RespuestaForm(opcion);
		}
	}
}
