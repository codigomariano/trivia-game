package ar.com.codigomariano.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.com.codigomariano.domain.Respuesta;
import ar.com.codigomariano.forms.RespuestaForm;

@Component
public class RespuestaFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RespuestaForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RespuestaForm form = (RespuestaForm) target;
		
		if(form.getTexto() == null || form.getTexto().isBlank()) {
			errors.rejectValue("respuestas["+form.getOpcion().ordinal()+"]"+".texto", "respuesta.empty", new String[] {form.getOpcion().name()}, null);
		}else if(form.getTexto().length() > Respuesta.TEXTO_MAX_LENGTH) {
			errors.rejectValue("respuestas["+form.getOpcion().ordinal()+"]"+".texto", "respuesta.max.length", new Object[] {form.getOpcion().name(), Respuesta.TEXTO_MAX_LENGTH}, null);
		}
	}
}
