package ar.com.codigomariano.services;

import org.springframework.stereotype.Service;

import ar.com.codigomariano.domain.preguntas.Pregunta;
import ar.com.codigomariano.forms.PreguntaForm;
import ar.com.codigomariano.repositories.PreguntaRepository;

@Service
public class PreguntaServiceImpl extends CRUDServiceImpl<Pregunta, PreguntaForm, PreguntaRepository> implements PreguntaService {

	@Override
	public void actualizar(PreguntaForm form) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected PreguntaForm emptyForm() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
