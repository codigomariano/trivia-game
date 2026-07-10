package ar.com.codigomariano.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.exceptions.MultipleUsersFoundException;
import ar.com.codigomariano.forms.LoginForm;
import ar.com.codigomariano.helpers.ValidationUtils;
import ar.com.codigomariano.services.UsuarioService;

@Component
public class LoginFormValidator implements Validator{
	@Autowired
	private UsuarioService servicio;

	
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginForm form = (LoginForm) target;
		
		if(ValidationUtils.isEmpty(form.getEmail())) {
			errors.rejectValue("email", "email.empty");
		} else {
			try {
				
				Usuario user = 	this.servicio.obtener(form.getEmail(), null);
				if(user == null) errors.rejectValue("email", "email.not.exists");
					
			}catch(MultipleUsersFoundException ex) {
				errors.rejectValue("email", "email.multiple.found");
			}
		}
	}

}
