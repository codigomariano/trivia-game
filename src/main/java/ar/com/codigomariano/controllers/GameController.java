package ar.com.codigomariano.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class GameController extends BaseController {
	public static final String GAME_URL = "/game";
	private static final String GAME_WELCOME_VIEW = "/game/welcome";
	
	
	@GetMapping(value = GAME_URL)
	public String init(HttpSession session) {
		String finalURL = GAME_WELCOME_VIEW;
		
		if(!estaUsuarioLogueado(session)) {
			finalURL = redirect(LoginController.SIGN_IN_URL);
		}
		
		return finalURL;
	}
}
