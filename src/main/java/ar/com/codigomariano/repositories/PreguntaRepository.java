package ar.com.codigomariano.repositories;

import java.util.List;

import ar.com.codigomariano.domain.preguntas.Pregunta;
import ar.com.codigomariano.forms.PreguntaForm;


public interface PreguntaRepository<P extends Pregunta<F>, F extends PreguntaForm> extends BaseRepository<P> {

	List<P> findByEliminada(Boolean eliminada);
	
}
