package ar.com.codigomariano.repositories;

import org.springframework.stereotype.Repository;

import ar.com.codigomariano.domain.preguntas.PreguntaBinaria;
import ar.com.codigomariano.forms.PreguntaBinariaForm;

@Repository
public interface PreguntaBinariaRepository extends PreguntaRepository<PreguntaBinaria, PreguntaBinariaForm> {

}
