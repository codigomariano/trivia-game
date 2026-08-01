package ar.com.codigomariano.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController extends BaseController{
	public static final String LOGOUT_URL = "/logout";
	
	
	@GetMapping(value = LOGOUT_URL)
	public String logout() {
		SecurityContextHolder.getContext().setAuthentication(null);

		SecurityContextHolder.clearContext();
		
		return redirect(LoginController.LOGIN_URL);
	}
}
