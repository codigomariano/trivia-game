package ar.com.codigomariano.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.com.codigomariano.services.PreguntaService;
import ar.com.codigomariano.services.UsuarioService;

@Controller
public class DashboardController extends AdminController {
	public static final String DASHBOARD_URL = ADMIN_URL + "/dashboard";
	private static final String DASHBOARD_VIEW = ADMIN_VIEW + "/dashboard";
	
	private final String CANT_PREGUNTAS_KEY = "cant_preguntas";
	private final String CANT_USUARIOS_KEY = "cant_usuarios";
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PreguntaService preguntaService;
	
	
	@GetMapping(value = DASHBOARD_URL)
	public String view(Model model) {
		model.addAttribute(CANT_USUARIOS_KEY, this.usuarioService.cantidadEntidades());
		model.addAttribute(CANT_PREGUNTAS_KEY, this.preguntaService.cantidadEntidades());

		return DASHBOARD_VIEW;
	}
}
