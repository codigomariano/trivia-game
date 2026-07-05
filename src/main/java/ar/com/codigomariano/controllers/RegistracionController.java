package ar.com.codigomariano.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.codigomariano.forms.RegistracionForm;

@Controller
public class RegistracionController extends BaseController{
	public static final String SIGN_UP_URL = "/signUp";
	public static final String REGISTRACION_URL = "/registro";
	
	
	@GetMapping(value = SIGN_UP_URL)
	public String init(Model model) {
		model.addAttribute(FORM_ATTRIBUTE, new RegistracionForm());
		return "signUp";
	}
	
	@PostMapping(value = REGISTRACION_URL)
	public String registracion(@ModelAttribute(FORM_ATTRIBUTE) RegistracionForm formulario) {
		formulario.getEmail();
		return redirect("/");
	}
}
