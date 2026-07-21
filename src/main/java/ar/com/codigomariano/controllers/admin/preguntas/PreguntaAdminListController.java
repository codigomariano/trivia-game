package ar.com.codigomariano.controllers.admin.preguntas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.com.codigomariano.domain.preguntas.Pregunta;
import ar.com.codigomariano.enums.PreguntaType;
import ar.com.codigomariano.services.PreguntaBinariaService;
import ar.com.codigomariano.services.PreguntaMultipleService;

@Controller
public class PreguntaAdminListController extends PreguntaAdminController {
	public static final String LIST_PREGUNTAS_URL = PREGUNTAS_URL + "/list";
	public static final String CHOOSE_PREGUNTAS_URL = PREGUNTAS_URL + "/choose";
	
	private static final String LIST_PREGUNTAS_VIEW = PREGUNTAS_VIEW + "/list";
	private static final String CHOOSE_PREGUNTAS_VIEW = PREGUNTAS_VIEW + "/selector";
	
	private final String TIPOS_PREGUNTAS_KEY = "preguntas_type";
	
	
	@Autowired
	private PreguntaBinariaService serviceBinaria;
	@Autowired
	private PreguntaMultipleService serviceMultiple;

	
	@GetMapping(value = LIST_PREGUNTAS_URL)
	public String init(Model model) {
		List<Pregunta<?>> preguntas = new ArrayList<Pregunta<?>>();
		
		preguntas.addAll(serviceBinaria.listAll());
		preguntas.addAll(serviceMultiple.listAll());
		
		model.addAttribute(LIST_ATTRIBUTE, preguntas);
		
		return LIST_PREGUNTAS_VIEW;
	}
	
	@GetMapping(value = CHOOSE_PREGUNTAS_URL)
	public String choose(Model model) {
		model.addAttribute(TIPOS_PREGUNTAS_KEY, PreguntaType.values());
		
		return CHOOSE_PREGUNTAS_VIEW;
	}
}
