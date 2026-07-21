package ar.com.codigomariano.services.preguntas;

import ar.com.codigomariano.domain.preguntas.PreguntaBinaria;
import ar.com.codigomariano.enums.Categoria;
import ar.com.codigomariano.forms.PreguntaBinariaForm;
import ar.com.codigomariano.services.PreguntaBinariaService;

public class PreguntaBinariaServiceTest extends PreguntaServiceTest<PreguntaBinaria, PreguntaBinariaForm, PreguntaBinariaService> {

	
	@Override
	protected PreguntaBinaria crearEntidadValida() {
		return new PreguntaBinaria("AB-001", "Na es el símbolo del Sodio", Categoria.CIENCIA);
	}
}
