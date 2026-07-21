package ar.com.codigomariano.repositories;

import org.springframework.stereotype.Repository;

import ar.com.codigomariano.domain.preguntas.PreguntaMultiple;
import ar.com.codigomariano.forms.PreguntaMultipleForm;

@Repository
public interface PreguntaMultipleRepository extends PreguntaRepository<PreguntaMultiple, PreguntaMultipleForm> {

}
