package ar.com.codigomariano.controllers.user;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.codigomariano.controllers.BaseController;
import ar.com.codigomariano.controllers.LoginController;
import ar.com.codigomariano.domain.Imagen;
import ar.com.codigomariano.forms.ProfileForm;
import ar.com.codigomariano.services.ImagenService;
import ar.com.codigomariano.services.UsuarioService;
import ar.com.codigomariano.session.InfoSession;
import ar.com.codigomariano.validators.ProfileFormValidator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController extends BaseController {
	public static final String VIEW_PROFILE_URL = "/profile/view";
	public static final String VIEW_PICTURE_URL = "/profile/picture/view";
	public static final String SAVE_PROFILE_URL = "/profile/save";
	private static final String PROFILE_VIEW = "/user/profile";
	
	@Autowired
	private UsuarioService service;
	@Autowired
	private ImagenService imgService;
	
	@Autowired
	private ProfileFormValidator validator;
	
	
	@InitBinder(value = FORM_ATTRIBUTE)
	void initFormValidator(WebDataBinder binder) {
		binder.addValidators(this.validator);
	}
	
	
	@GetMapping(value =  VIEW_PROFILE_URL)
	public String viewProfile(HttpSession session, Model model) {
		String finalURL = PROFILE_VIEW;
		
		if(estaUsuarioLogueado(session)) {
			InfoSession info = getInfoSession(session);
			
			ProfileForm form = this.service.prepareProfile(info.getEmail());
			model.addAttribute(FORM_ATTRIBUTE, form);
			
		} else {
			finalURL = redirect(LoginController.SIGN_IN_URL);
		}
		
		
		return finalURL;
	}
	
	
	@GetMapping(value = VIEW_PICTURE_URL)
	public void viewPicture(HttpServletResponse response, @RequestParam(name = ID_PARAMETER, defaultValue = "-1") Long id) {
		Imagen imagen = this.imgService.obtener(id);
		
		if(imagen != null) {
			 try {
			      InputStream is = new ByteArrayInputStream(imagen.getContenido());

			      IOUtils.copy(is, response.getOutputStream());
			      
			      response.flushBuffer();
			      
			    } catch (IOException ex) {
			    	System.out.println("La imagen no pude ser cargada");
			    }
		}
	}
	
	
	@PostMapping(value = SAVE_PROFILE_URL)
	public String saveProfile(@Validated @ModelAttribute(name = FORM_ATTRIBUTE) ProfileForm form, BindingResult results) {
		if(results.hasErrors()) return PROFILE_VIEW;
		
		this.service.actualizar(form);
		
		return redirect(VIEW_PROFILE_URL);
	}
}
