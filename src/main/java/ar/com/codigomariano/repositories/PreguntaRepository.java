package ar.com.codigomariano.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.codigomariano.domain.preguntas.Pregunta;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {

}
