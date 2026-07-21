package ar.com.codigomariano.controllers.admin.preguntas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.codigomariano.domain.preguntas.PreguntaMultiple;
import ar.com.codigomariano.forms.DeletionForm;
import ar.com.codigomariano.forms.PreguntaMultipleForm;
import ar.com.codigomariano.services.PreguntaMultipleService;
import ar.com.codigomariano.validators.PreguntaMultipleFormValidator;

@Controller
public class PreguntaMultipleController extends PreguntaAdminABMController<PreguntaMultiple, PreguntaMultipleForm, PreguntaMultipleService, PreguntaMultipleFormValidator> {
	public static final String EDIT_PREGUNTA_URL = EDITION_PREGUNTA  + "/MULTIPLE";
	public static final String SAVE_PREGUNTA_URL = SAVING_PREGUNTA + "/MULTIPLE";
	public static final String DELETE_PREGUNTA_URL = DELETION_PREGUNTA + "/MULTIPLE";
	
	
	@GetMapping(value = EDIT_PREGUNTA_URL)
	public String edit(Model model, 
			@RequestParam(name = ID_PARAMETER, defaultValue = "-1") Long id) {
		
		return edition(model, id);
	}


	@PostMapping(value = SAVE_PREGUNTA_URL)
	public String save(@Validated @ModelAttribute(name = FORM_ATTRIBUTE) PreguntaMultipleForm formulario, BindingResult resultados) {
		return saving(formulario, resultados);
	}
	
	
	@PostMapping(value = DELETE_PREGUNTA_URL)
	public String delete(@ModelAttribute(name = FORM_ATTRIBUTE) DeletionForm formulario) {
		return deletion(formulario);
	}
	
	
	@Override
	protected PreguntaMultiple buildNewQuestion(PreguntaMultipleForm form) {
		PreguntaMultiple pm = new PreguntaMultiple(form.getCodigo(), form.getPregunta(), form.getCategoria());
		pm.updateRespuestas(form.getRespuestas());
		
		return pm;
	}
}
