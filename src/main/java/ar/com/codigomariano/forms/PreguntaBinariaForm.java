package ar.com.codigomariano.forms;

import ar.com.codigomariano.enums.Opcion;
import ar.com.codigomariano.enums.OpcionBinaria;
import ar.com.codigomariano.enums.PreguntaType;

public class PreguntaBinariaForm extends PreguntaForm {
	private OpcionBinaria correcta;
	
	
	public PreguntaBinariaForm() {
		super(PreguntaType.BINARIA);
	}
	
	@Override
	public Opcion[] getOpciones() {
		return OpcionBinaria.values();
	}

	public OpcionBinaria getCorrecta() {
		return correcta;
	}

	public void setCorrecta(OpcionBinaria correcta) {
		this.correcta = correcta;
	}
}
