package ar.com.codigomariano.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.codigomariano.exceptions.MultipleUsersFoundException;
import ar.com.codigomariano.forms.LoginForm;
import ar.com.codigomariano.services.JWTService;
import ar.com.codigomariano.validators.LoginFormValidator;

@RestController
public class LoginAPI extends BaseAPIController {
	public static final String LOGIN_URL = BASE_URL + "/login";
	@Autowired
	private LoginFormValidator validator;
	@Autowired
	private JWTService service;
	
	
	@PostMapping(value = LOGIN_URL,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@RequestBody LoginForm form) {
		ResponseEntity<String> respuesta = null;
		
		Errors errors = this.validator.validateObject(form);
		if(errors.hasErrors()) {
			respuesta = ResponseEntity.badRequest().body("No se pudo autenticar el usuario");
		} else {
			String token;
			try {
				
				token = this.service.login(form.getEmail());
				respuesta = ResponseEntity.ok(token);
			
			}catch(MultipleUsersFoundException | UsernameNotFoundException e) {
				respuesta = ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		
		return respuesta;
	}
	
}
