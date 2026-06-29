package ar.com.codigomariano.domain.preguntas;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ar.com.codigomariano.domain.BaseEntityTest;
import ar.com.codigomariano.helpers.ValidationUtils;

public abstract class PreguntaEntityTest<T extends Pregunta> extends BaseEntityTest<T> {

	
	@Override
	protected void validarEntidadExitosa(T entidad) {
		assertNotNull(entidad.getCodigo());
		assertTrue(ValidationUtils.tieneMenosDe(entidad.getCodigo(), Pregunta.CODIGO_MAX_LENGTH));
		assertNotNull(entidad.getCategoria());
	}
}
