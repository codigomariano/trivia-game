package ar.com.codigomariano.services;

import org.springframework.stereotype.Service;

import ar.com.codigomariano.domain.preguntas.PreguntaMultiple;
import ar.com.codigomariano.forms.PreguntaMultipleForm;
import ar.com.codigomariano.repositories.PreguntaMultipleRepository;

@Service
public class PreguntaMultipleServiceImpl extends PreguntaServiceImpl<PreguntaMultiple, PreguntaMultipleForm, PreguntaMultipleRepository> implements PreguntaMultipleService {

	
	@Override
	protected PreguntaMultipleForm emptyForm() {
		return new PreguntaMultipleForm();
	}
}
