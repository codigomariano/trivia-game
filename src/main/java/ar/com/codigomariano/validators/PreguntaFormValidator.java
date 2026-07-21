package ar.com.codigomariano.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.com.codigomariano.domain.preguntas.Pregunta;
import ar.com.codigomariano.forms.PreguntaForm;

public abstract class PreguntaFormValidator<F extends PreguntaForm> implements Validator {

	
	@Override
	public void validate(Object target, Errors errors) {
		PreguntaForm form = (PreguntaForm) target;
		
		if(form.getCodigo() == null || form.getCodigo().isBlank()) {
			errors.rejectValue("codigo", "code.empty");
		}else if(form.getCodigo().length() > Pregunta.CODIGO_MAX_LENGTH) {
			errors.rejectValue("codigo", "code.max.length", new Integer[] {Pregunta.CODIGO_MAX_LENGTH}, null);
		}
		
		if(form.getPregunta() == null || form.getPregunta().isBlank()) {
			errors.rejectValue("pregunta", "question.empty");
		}else if(form.getPregunta().length() > Pregunta.TEXTO_MAX_LENGTH) {
			errors.rejectValue("pregunta", "question.max.length", new Integer[] {Pregunta.TEXTO_MAX_LENGTH}, null);
		}
		
		if(form.getCategoria() == null) {
			errors.rejectValue("categoria", "categoria.manatory");
		}
		
		if(form.getPuntos() == null) {
			errors.rejectValue("puntos", "puntos.mandatory");
		} else if (form.getPuntos() < Pregunta.PUNTOS_MIN_VALUE || form.getPuntos() > Pregunta.PUNTOS_MAX_VALUE) {
			errors.rejectValue("puntos", "puntos.range", new Integer[] {Pregunta.PUNTOS_MIN_VALUE, Pregunta.PUNTOS_MAX_VALUE}, null);
		}
	}
}
