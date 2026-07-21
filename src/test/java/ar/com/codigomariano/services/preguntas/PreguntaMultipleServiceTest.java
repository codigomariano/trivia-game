package ar.com.codigomariano.services.preguntas;

import ar.com.codigomariano.domain.preguntas.PreguntaMultiple;
import ar.com.codigomariano.enums.Categoria;
import ar.com.codigomariano.forms.PreguntaMultipleForm;
import ar.com.codigomariano.services.PreguntaMultipleService;

public class PreguntaMultipleServiceTest extends PreguntaServiceTest<PreguntaMultiple, PreguntaMultipleForm, PreguntaMultipleService> {

	
	@Override
	protected PreguntaMultiple crearEntidadValida() {
		return new PreguntaMultiple("AM-001", "Cuál es la capital de Argentina?", Categoria.GEOGRAFIA);
	}
}
