package ar.com.codigomariano.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController extends BaseController {

	@GetMapping("/")
	public String init() {
		return redirect(HomeController.HOME_URL);
	}
}
