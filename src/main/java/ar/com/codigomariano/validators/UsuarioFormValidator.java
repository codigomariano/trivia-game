package ar.com.codigomariano.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.exceptions.MultipleUsersFoundException;
import ar.com.codigomariano.forms.UsuarioForm;
import ar.com.codigomariano.helpers.ValidationUtils;
import ar.com.codigomariano.services.UsuarioService;

@Component
public class UsuarioFormValidator implements Validator {
	@Autowired
	private UsuarioService servicio;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UsuarioForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UsuarioForm form = (UsuarioForm) target;
		
		if(ValidationUtils.isEmpty(form.getEmail())) {
			errors.rejectValue("email", "email.empty");
		} else if (ValidationUtils.tieneMasDe(form.getEmail(), Usuario.EMAIL_MAX_LENGTH)) {
			errors.rejectValue("email", "email.max.length", new Integer[] {Usuario.EMAIL_MAX_LENGTH}, null);
		} else {
			try {
				
				Usuario user = 	this.servicio.obtener(form.getEmail(), form.getId());
				if(user != null) errors.rejectValue("email", "email.already.exists");
					
			}catch(MultipleUsersFoundException ex) {
				errors.rejectValue("email", "email.multiple.found");
			}
		}
		
		if(ValidationUtils.isEmpty(form.getUsername())) {
			errors.rejectValue("username", "username.empty");
		} else if(ValidationUtils.tieneMenosDe(form.getUsername(), Usuario.USERNAME_MIN_LENGTH)) {
			errors.rejectValue("username", "username.min.length", new Integer[] {Usuario.USERNAME_MIN_LENGTH}, null);
		} else if(ValidationUtils.tieneMasDe(form.getUsername(), Usuario.USERNAME_MAX_LENGTH)) {
			errors.rejectValue("username", "username.max.length", new Integer[] {Usuario.USERNAME_MAX_LENGTH}, null);
		}
		
		
		if(!ValidationUtils.isEmpty(form.getFullName()) && 
				ValidationUtils.tieneMasDe(form.getFullName(), Usuario.FULL_NAME_MAX_LENGTH)) {
			errors.rejectValue("fullName", "fullname.max.length", new Integer[] {Usuario.FULL_NAME_MAX_LENGTH}, null);
		}
	}
}
