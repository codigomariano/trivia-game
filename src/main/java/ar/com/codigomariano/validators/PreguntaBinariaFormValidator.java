package ar.com.codigomariano.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import ar.com.codigomariano.forms.PreguntaBinariaForm;

@Component
public class PreguntaBinariaFormValidator extends PreguntaFormValidator<PreguntaBinariaForm> {

	
	@Override
	public boolean supports(Class<?> clazz) {
		return PreguntaBinariaForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		super.validate(target, errors);
		
		PreguntaBinariaForm form = (PreguntaBinariaForm) target;
		
		if(form.getCorrecta() == null) {
			errors.rejectValue("correcta", "question.binaria.correcta");
		}
	}
}
