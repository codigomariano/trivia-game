package ar.com.codigomariano.services.preguntas;

import ar.com.codigomariano.domain.preguntas.Pregunta;
import ar.com.codigomariano.domain.preguntas.PreguntaBinaria;
import ar.com.codigomariano.enums.Categoria;

public class PreguntaBinariaServiceTest extends PreguntaServiceTest {

	@Override
	protected Pregunta crearEntidadValida() {
		PreguntaBinaria pregunta = new PreguntaBinaria("AB-001", "Na es el símbolo del Sodio", Categoria.CIENCIA);
		return pregunta;
	}
}
