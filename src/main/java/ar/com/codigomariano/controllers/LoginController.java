package ar.com.codigomariano.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.codigomariano.controllers.user.ProfileController;
import ar.com.codigomariano.forms.LoginForm;
import ar.com.codigomariano.services.UsuarioService;
import ar.com.codigomariano.validators.LoginFormValidator;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController extends BaseWebController{
	public static final String SIGN_IN_URL = BASE_URL + "/signIn";
	public static final String LOGIN_URL = BASE_URL + "/login";
	private static final String SIGN_IN_VIEW = "signIn";
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private LoginFormValidator validator;
	
	
	@InitBinder(value = FORM_ATTRIBUTE)
	void initFormValidator(WebDataBinder binder) {
		binder.addValidators(this.validator);
	}
	
	@GetMapping(value = SIGN_IN_URL)
	public String init(Model model) {
		model.addAttribute(FORM_ATTRIBUTE, new LoginForm());
		return SIGN_IN_VIEW;
	}
	
	@PostMapping(value = LOGIN_URL)
	public String login(HttpSession session, @Validated @ModelAttribute(FORM_ATTRIBUTE) LoginForm formulario, BindingResult results) {
		if(results.hasErrors()) return SIGN_IN_VIEW;
		
		this.service.login(formulario.getEmail());
		
		SecurityContext context = SecurityContextHolder.getContext();
		
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
		
		return redirect(ProfileController.VIEW_PROFILE_URL);
	}
}
