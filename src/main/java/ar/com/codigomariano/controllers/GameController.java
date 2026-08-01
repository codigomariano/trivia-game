package ar.com.codigomariano.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ar.com.codigomariano.session.InfoSession;
import jakarta.servlet.http.HttpSession;

@Controller
public class GameController extends BaseController {
	public static final String GAME_URL = "/game";
	public static final String GAME_AS_GUEST_URL = "/game/guest";
	private static final String GAME_WELCOME_VIEW = "/game/welcome";
	
	
	@GetMapping(value = GAME_URL)
	public String game(HttpSession session) {
		session.setAttribute(INFO_ATTRIBUTE, new InfoSession());
		return GAME_WELCOME_VIEW;
	}
	
	@GetMapping(value = GAME_AS_GUEST_URL)
	public String gameAsGuest(HttpSession session) {	
		session.setAttribute(INFO_ATTRIBUTE, new InfoSession());
		return GAME_WELCOME_VIEW;
	}
}
