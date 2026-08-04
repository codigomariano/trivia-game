package ar.com.codigomariano.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.codigomariano.forms.RegistracionForm;
import ar.com.codigomariano.services.UsuarioService;
import ar.com.codigomariano.validators.RegistracionFormValidator;

@Controller
public class RegistracionController extends BaseWebController{	
	public static final String SIGN_UP_URL = BASE_URL + "/signUp";
	public static final String REGISTRACION_URL = BASE_URL + "/registro";
	private static final String SIGN_UP_VIEW = "signUp";
	
	@Autowired
	private UsuarioService servicio;
	@Autowired
	private RegistracionFormValidator validator;
	
	
	@InitBinder(value = FORM_ATTRIBUTE)
	void initFormValidator(WebDataBinder binder) {
		binder.addValidators(this.validator);
	}
	
	@GetMapping(value = SIGN_UP_URL)
	public String init(Model model) {
		model.addAttribute(FORM_ATTRIBUTE, new RegistracionForm());
		return SIGN_UP_VIEW;
	}
	
	@PostMapping(value = REGISTRACION_URL)
	public String registracion(@Validated @ModelAttribute(FORM_ATTRIBUTE) RegistracionForm formulario, BindingResult results) {
		if(results.hasErrors()) return SIGN_UP_VIEW;
		
		this.servicio.registrar(formulario.getEmail());
		
		return redirect("/");
	}
}
