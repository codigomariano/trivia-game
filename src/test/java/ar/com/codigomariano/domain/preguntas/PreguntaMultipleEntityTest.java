package ar.com.codigomariano.domain.preguntas;

import ar.com.codigomariano.enums.Categoria;

public class PreguntaMultipleEntityTest extends PreguntaEntityTest<PreguntaMultiple> {

	@Override
	protected PreguntaMultiple crearEntidadValida() {
		return new PreguntaMultiple("A88", "Cuántos equipos juegan un partido de futbol?", Categoria.DEPORTES);
	}

}
