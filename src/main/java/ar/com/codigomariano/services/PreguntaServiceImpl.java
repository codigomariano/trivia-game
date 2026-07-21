package ar.com.codigomariano.services;

import java.util.Optional;

import ar.com.codigomariano.domain.preguntas.Pregunta;
import ar.com.codigomariano.forms.DeletionForm;
import ar.com.codigomariano.forms.PreguntaForm;
import ar.com.codigomariano.repositories.PreguntaRepository;


public abstract class PreguntaServiceImpl<T extends Pregunta<F>, F extends PreguntaForm, R extends PreguntaRepository<T, F>> extends CRUDServiceImpl<T, F, R> implements PreguntaService<T, F> {


	@Override
	public void eliminar(DeletionForm form) {
		Optional<T> option = repository().findById(form.getId());
		
		if(option.isPresent()) {
			T pregunta = option.get();
			pregunta.setEliminada(Boolean.TRUE);
			guardar(pregunta);
		}
	}
}
