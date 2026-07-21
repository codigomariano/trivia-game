package ar.com.codigomariano.services;

import ar.com.codigomariano.domain.preguntas.Pregunta;
import ar.com.codigomariano.forms.PreguntaForm;

public interface PreguntaService<T extends Pregunta<F>, F extends PreguntaForm> extends CRUDService<T, F>{


	
}
