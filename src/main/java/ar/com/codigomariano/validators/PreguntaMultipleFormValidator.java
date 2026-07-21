package ar.com.codigomariano.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import ar.com.codigomariano.forms.PreguntaMultipleForm;
import ar.com.codigomariano.forms.RespuestaForm;

@Component
public class PreguntaMultipleFormValidator extends PreguntaFormValidator<PreguntaMultipleForm> {
	@Autowired
	private RespuestaFormValidator respuestaValidator;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PreguntaMultipleForm.class.equals(clazz);
	}
	
	
	@Override
	public void validate(Object target, Errors errors) {
		super.validate(target, errors);
		
		
		PreguntaMultipleForm form = (PreguntaMultipleForm) target;
		
		boolean alMenosUnaMarcada = false;
		for (RespuestaForm respuesta : form.getRespuestas()) {
			this.respuestaValidator.validate(respuesta, errors);
			if(respuesta.getCorrecta()) alMenosUnaMarcada = true;
		}
		
		if(!alMenosUnaMarcada) {
			errors.rejectValue("respuestas", "respuestas.manatory");
		}
	}
}
