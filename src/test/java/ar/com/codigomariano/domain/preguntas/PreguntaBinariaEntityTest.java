package ar.com.codigomariano.domain.preguntas;

import ar.com.codigomariano.enums.Categoria;

public class PreguntaBinariaEntityTest extends PreguntaEntityTest<PreguntaBinaria> {

	@Override
	protected PreguntaBinaria crearEntidadValida() {
		return new PreguntaBinaria("AX-001", "Argentina está en Amércia?", Categoria.GEOGRAFIA, Boolean.TRUE);
	}

}
