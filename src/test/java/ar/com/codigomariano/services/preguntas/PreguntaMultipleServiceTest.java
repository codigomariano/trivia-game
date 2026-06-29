package ar.com.codigomariano.services.preguntas;

import ar.com.codigomariano.domain.preguntas.Pregunta;
import ar.com.codigomariano.domain.preguntas.PreguntaMultiple;
import ar.com.codigomariano.enums.Categoria;

public class PreguntaMultipleServiceTest extends PreguntaServiceTest {

	@Override
	protected Pregunta crearEntidadValida() {
		Pregunta pregunta = new PreguntaMultiple("AM-001", "Cuál es la capital de Argentina?", Categoria.GEOGRAFIA);
		return pregunta;
	}
}
