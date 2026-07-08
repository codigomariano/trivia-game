package ar.com.codigomariano.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.exceptions.MultipleUsersFoundException;
import ar.com.codigomariano.forms.LoginForm;
import ar.com.codigomariano.forms.RegistracionForm;
import ar.com.codigomariano.helpers.ValidationUtils;
import ar.com.codigomariano.services.UsuarioService;

@Component
public class RegistracionFormValidator implements Validator {
	@Autowired
	private UsuarioService servicio;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RegistracionForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RegistracionForm form = (RegistracionForm) target;
		
		if(form.getEmail() == null || form.getEmail().isBlank()) {
			errors.rejectValue("email", "email.empty");
		} else if (ValidationUtils.tieneMasDe(form.getEmail(), Usuario.EMAIL_MAX_LENGTH)) {
			errors.rejectValue("email", "email.max.length", new Integer[] {Usuario.EMAIL_MAX_LENGTH}, null);
		} else {
			try {
				
				Usuario user = 	this.servicio.obtener(form.getEmail(), null);
				if(user != null) errors.rejectValue("email", "email.already.exists");
					
			}catch(MultipleUsersFoundException ex) {
				errors.rejectValue("email", "email.multiple.found");
			}
		}
	}

}
