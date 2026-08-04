package ar.com.codigomariano.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.codigomariano.dtos.DTO;
import ar.com.codigomariano.forms.RegistracionForm;
import ar.com.codigomariano.services.UsuarioService;
import ar.com.codigomariano.validators.RegistracionFormValidator;

@RestController
public class UsuarioAPI extends BaseAPIController {
	public static final String BASE_USERS_API = BASE_URL + "/users";
	public static final String LIST_USERS_URL = BASE_USERS_API;
	public static final String REGISTER_USERS_URL = BASE_USERS_API + "/register";
	
	@Autowired
	private UsuarioService servicio;
	@Autowired
	private RegistracionFormValidator validator;
	
	
	@GetMapping(value = LIST_USERS_URL,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DTO>> list() {
		List<DTO> usuarios = this.servicio.exponer();
		
		return ResponseEntity.ok(usuarios);
	}
	
	@PostMapping(value = REGISTER_USERS_URL,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registar(@RequestBody RegistracionForm form) {
		ResponseEntity<String> respuesta = null;
		
		Errors errors = this.validator.validateObject(form);
		if(errors.hasErrors()) {
			respuesta = ResponseEntity.badRequest().body("No se pudo registrar el usuario");
		} else {
			this.servicio.registrar(form.getEmail());
			respuesta = ResponseEntity.status(HttpStatus.CREATED).build();
		}
		
		return respuesta;
	}
}
