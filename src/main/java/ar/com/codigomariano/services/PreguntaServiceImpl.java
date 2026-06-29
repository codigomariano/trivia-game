package ar.com.codigomariano.services;

import org.springframework.stereotype.Service;

import ar.com.codigomariano.domain.preguntas.Pregunta;
import ar.com.codigomariano.repositories.PreguntaRepository;

@Service
public class PreguntaServiceImpl extends CRUDServiceImpl<Pregunta, PreguntaRepository> implements PreguntaService {

	
}
