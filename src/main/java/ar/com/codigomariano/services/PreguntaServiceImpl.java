package ar.com.codigomariano.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.codigomariano.domain.preguntas.Pregunta;
import ar.com.codigomariano.repositories.PreguntaRepository;

@Service
public class PreguntaServiceImpl implements PreguntaService {
	@Autowired
	private PreguntaRepository repositorio;
	
	@Override
	public void guadar(Pregunta pregunta) {
		this.repositorio.save(pregunta);
	}
}
