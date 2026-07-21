package ar.com.codigomariano.services.preguntas;

import ar.com.codigomariano.domain.preguntas.Pregunta;
import ar.com.codigomariano.forms.PreguntaForm;
import ar.com.codigomariano.services.BaseServiceTest;
import ar.com.codigomariano.services.PreguntaService;

public abstract class PreguntaServiceTest<P extends Pregunta<F>, F extends PreguntaForm, S extends PreguntaService<P, F>> extends BaseServiceTest<P, F, S> {

}
