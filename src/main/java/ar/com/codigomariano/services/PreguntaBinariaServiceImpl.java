package ar.com.codigomariano.services;

import org.springframework.stereotype.Service;

import ar.com.codigomariano.domain.preguntas.PreguntaBinaria;
import ar.com.codigomariano.forms.PreguntaBinariaForm;
import ar.com.codigomariano.repositories.PreguntaBinariaRepository;

@Service
public class PreguntaBinariaServiceImpl extends PreguntaServiceImpl<PreguntaBinaria, PreguntaBinariaForm, PreguntaBinariaRepository> implements PreguntaBinariaService {


	@Override
	protected PreguntaBinariaForm emptyForm() {
		return new PreguntaBinariaForm();
	}
}
