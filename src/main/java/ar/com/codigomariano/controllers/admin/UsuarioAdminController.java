package ar.com.codigomariano.controllers.admin;

import java.util.List;

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
import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.forms.DeletionForm;
import ar.com.codigomariano.forms.UsuarioForm;
import ar.com.codigomariano.services.UsuarioService;
import ar.com.codigomariano.validators.UsuarioFormValidator;

@Controller
public class UsuarioAdminController extends BaseController {
	public static final String LIST_USERS_URL = "/admin/user/list";
	public static final String EDIT_USERS_URL = "/admin/user/edit";
	public static final String SAVE_USERS_URL = "/admin/user/save";
	public static final String DELETE_USERS_URL = "/admin/user/delete";
	
	protected static final String LIST_USERS_VIEW = "/admin/users/list";
	protected static final String FORM_USERS_VIEW = "/admin/users/form";
	
	@Autowired
	private UsuarioService servicio;
	@Autowired
	private UsuarioFormValidator validator;
	
	
	@InitBinder(value = FORM_ATTRIBUTE)
	void initFormValidator(WebDataBinder binder) {
		binder.addValidators(this.validator);
	}
	
	
	@GetMapping(value = LIST_USERS_URL)
	public String init(Model modelo) {
		List<Usuario> usuarios = this.servicio.listAll();
		modelo.addAttribute(LIST_ATTRIBUTE, usuarios);
		
		return LIST_USERS_VIEW;
	}
	
	@GetMapping(value = EDIT_USERS_URL)
	public String edit(Model modelo, @RequestParam(name = ID_PARAMETER, defaultValue = "-1") Long id) {
		UsuarioForm form = this.servicio.prepareEntity(id);
				
		modelo.addAttribute(FORM_ATTRIBUTE, form);
		
		return FORM_USERS_VIEW;
	}
	
	
	@PostMapping(value = SAVE_USERS_URL)
	public String save(@Validated @ModelAttribute(FORM_ATTRIBUTE) UsuarioForm form, BindingResult results) {
		if(results.hasErrors()) return FORM_USERS_VIEW;
		
		if(form.esCreacion()) {
			this.servicio.registrar(form.getEmail(), form.getUsername(), form.getFullName());			
		} else {
			this.servicio.actualizar(form);
		}
		
		return redirect(LIST_USERS_URL);
	}
	
	
	@PostMapping(value = DELETE_USERS_URL)
	public String delete(DeletionForm form) {
		
		this.servicio.eliminar(form);
		
		return redirect(LIST_USERS_URL);
	}
}
