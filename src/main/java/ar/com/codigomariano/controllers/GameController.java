package ar.com.codigomariano.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ar.com.codigomariano.session.Partida;
import jakarta.servlet.http.HttpSession;

@Controller
public class GameController extends BaseWebController {
	public static final String GAME_URL = BASE_URL + "/game";
	public static final String GAME_AS_GUEST_URL = GAME_URL +  "/guest";
	private static final String GAME_WELCOME_VIEW = "/game/welcome";
	
	@Value("${preguntas.por.partida}")
	private Integer preguntasPorPartida;
	
	@GetMapping(value = GAME_URL)
	public String game(HttpSession session) {
		session.setAttribute(INFO_ATTRIBUTE, new Partida());
		return GAME_WELCOME_VIEW;
	}
	
	@GetMapping(value = GAME_AS_GUEST_URL)
	public String gameAsGuest(HttpSession session) {	
		session.setAttribute(INFO_ATTRIBUTE, new Partida());
		return GAME_WELCOME_VIEW;
	}
}
