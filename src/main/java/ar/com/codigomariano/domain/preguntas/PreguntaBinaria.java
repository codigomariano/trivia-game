package ar.com.codigomariano.domain.preguntas;

import ar.com.codigomariano.dtos.DesafioDTO;
import ar.com.codigomariano.enums.Categoria;
import ar.com.codigomariano.enums.OpcionBinaria;
import ar.com.codigomariano.enums.PreguntaType;
import ar.com.codigomariano.forms.PreguntaBinariaForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "PREGUNTAS_BINARIAS")
public class PreguntaBinaria extends Pregunta<PreguntaBinariaForm> {
	@Column(name = "OPCION_CORRECTA")
	private boolean correcta;
	
	
	// Sólo para Hibernate
	PreguntaBinaria() {
		super();
	}
	
	public PreguntaBinaria(String codigo, String texto, Categoria categoria) {
		this(codigo, texto, categoria, false);
	}
	
	public PreguntaBinaria(String codigo, String texto, Categoria categoria, boolean correcta) {
		super(codigo, texto, categoria);
		this.correcta = correcta;
	}

	
	@Override
	public void copyPropertiesToForm(PreguntaBinariaForm form) {
		super.copyPropertiesToForm(form);
		form.setCorrecta(OpcionBinaria.getFromValue(isCorrecta()));
	}
	
	@Override
	public void updatePropertiesFromForm(PreguntaBinariaForm form) {
		super.updatePropertiesFromForm(form);
		setCorrecta(form.getCorrecta().getValor());
	}
	
	@Override
	public PreguntaType getType() {
		return PreguntaType.BINARIA;
	}
	
	@Override
	protected void setOpcionesParaDesafio(DesafioDTO desafio) {
		desafio.setOpciones(new String[] {OpcionBinaria.V.displayText(), OpcionBinaria.F.displayText()});
		
		OpcionBinaria correcta = OpcionBinaria.getFromValue(this.correcta);
		desafio.setIndexCorrecto(correcta.ordinal());
	}
	
	public boolean isCorrecta() {
		return correcta;
	}

	public void setCorrecta(boolean correcta) {
		this.correcta = correcta;
	}
}
