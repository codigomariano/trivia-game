package ar.com.codigomariano.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.codigomariano.dtos.DesafioDTO;
import ar.com.codigomariano.exceptions.NoMoreDesafiosException;
import ar.com.codigomariano.forms.DesafioForm;
import ar.com.codigomariano.services.GameService;
import ar.com.codigomariano.session.Partida;
import jakarta.servlet.http.HttpSession;

@Controller
public class GameController extends BaseWebController {
	public static final String GAME_URL = BASE_URL + "/game";
	public static final String GAME_AS_GUEST_URL = GAME_URL +  "/guest";
	public static final String QUESTION_URL = GAME_URL +  "/question";
	public static final String ANSWER_URL = GAME_URL +  "/answer";
	
	private static final String GAME_WELCOME_VIEW = "/game/welcome";
	private static final String DESAFIO_VIEW = "/game/trivia";
	private static final String DESAFIO_END_VIEW = "/game/trivia-end";
	
	@Autowired
	private GameService service;
	
	@Value("${preguntas.por.partida}")
	private Integer preguntasPorPartida;
	
	@GetMapping(value = GAME_URL)
	public String game(HttpSession session) {
		iniciarPartida(session);
		return GAME_WELCOME_VIEW;
	}
	
	@GetMapping(value = GAME_AS_GUEST_URL)
	public String gameAsGuest(HttpSession session) {	
		iniciarPartida(session);
		return GAME_WELCOME_VIEW;
	}
	
	@GetMapping(value = QUESTION_URL)
	public String desafio(HttpSession session, Model model) {
		Partida partida = (Partida) session.getAttribute(PARTIDA);
		
		try {
			
			DesafioDTO desafio = partida.siguienteDesafio();
			model.addAttribute(DESAFIO_ATTRIBUTE, desafio);
			model.addAttribute(FORM_ATTRIBUTE, new DesafioForm(desafio.getId()));
			
		}catch(NoMoreDesafiosException ex) {
			return DESAFIO_END_VIEW;
		}
		
		return DESAFIO_VIEW;
	}
	
	@PostMapping(value = ANSWER_URL)
	public String answer(HttpSession session, @ModelAttribute(FORM_ATTRIBUTE) DesafioForm form) {
		Partida partida = (Partida) session.getAttribute(PARTIDA);
		
		partida.actualizarPuntos(form.getIdPregunta(), form.getIndiceRespuestaElegia());
		
		return redirect(QUESTION_URL);
	}
	
	
	/**
	 * Inicia la partida obteniendo la cantidad máxima de desafíos configurada
	 * y almacenándolos en la sesión para que puedan ser utilizados por la
	 * capa de presentación.
	 */
	private void iniciarPartida(HttpSession session) {
		List<DesafioDTO> desafios = this.service.obtenerDesafiosParaPartida(this.preguntasPorPartida);
		
		session.setAttribute(PARTIDA, new Partida(desafios));
	}
}
