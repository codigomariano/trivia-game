package ar.com.codigomariano.domain.preguntas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ar.com.codigomariano.domain.Respuesta;
import ar.com.codigomariano.enums.Categoria;
import ar.com.codigomariano.enums.OpcionMultiple;
import ar.com.codigomariano.enums.PreguntaType;
import ar.com.codigomariano.forms.PreguntaMultipleForm;
import ar.com.codigomariano.forms.RespuestaForm;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PREGUNTAS_MULTIPLES")
public class PreguntaMultiple extends Pregunta<PreguntaMultipleForm> {
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "PREGUNTA_ID", referencedColumnName = "ID", nullable = false)
	private List<Respuesta> respuestas;
	
	
	// Sólo para Hibernate
	PreguntaMultiple() {
		super();
	}
	
	public PreguntaMultiple(String codigo, String texto, Categoria categoria) {
		super(codigo, texto, categoria);
		this.respuestas = new ArrayList<Respuesta>();
	}
	
	@Override
	public void copyPropertiesToForm(PreguntaMultipleForm form) {
		super.copyPropertiesToForm(form);
		
		for (Respuesta respuesta : this.respuestas) {
			respuesta.copyPropertiesToForm(form.getRespuestas()[respuesta.getOpcion().ordinal()]);
		}
	}
	
	@Override
	public void updatePropertiesFromForm(PreguntaMultipleForm form) {
		super.updatePropertiesFromForm(form);
		updateRespuestas(form.getRespuestas());
		
	}
	
	@Override
	public PreguntaType getType() {
		return PreguntaType.MULTIPLE;
	}
	
	public void updateRespuestas(RespuestaForm[] respuestas) {
		for (OpcionMultiple opcion : OpcionMultiple.values()) {
			RespuestaForm respuesta = respuestas[opcion.ordinal()];
			updateRespuesta(respuesta);
		}		
	}
	
	
	private void updateRespuesta(RespuestaForm form) {
		Optional<Respuesta> found = this.respuestas.stream().filter(r -> r.getOpcion().equals(form.getOpcion())).findFirst();
		
		if(found.isEmpty()) {
			this.respuestas.add(new Respuesta(form.getOpcion(), form.getTexto(), form.getCorrecta()));
		} else {
			found.get().updatePropertiesFromForm(form);
		}
	}
}
