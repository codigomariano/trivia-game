package ar.com.codigomariano.controllers.admin.preguntas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.codigomariano.domain.preguntas.PreguntaBinaria;
import ar.com.codigomariano.forms.DeletionForm;
import ar.com.codigomariano.forms.PreguntaBinariaForm;
import ar.com.codigomariano.services.PreguntaBinariaService;
import ar.com.codigomariano.validators.PreguntaBinariaFormValidator;

@Controller
public class PreguntaBinariaController extends PreguntaAdminABMController<PreguntaBinaria, PreguntaBinariaForm, PreguntaBinariaService, PreguntaBinariaFormValidator> {
	public static final String EDIT_PREGUNTA_URL = EDITION_PREGUNTA  + "/BINARIA";
	public static final String SAVE_PREGUNTA_URL = SAVING_PREGUNTA + "/BINARIA";
	public static final String DELETE_PREGUNTA_URL = DELETION_PREGUNTA + "/BINARIA";
	
	
	
	@GetMapping(value = EDIT_PREGUNTA_URL)
	public String edit(Model model, 
			@RequestParam(name = ID_PARAMETER, defaultValue = "-1") Long id) {
		
		return edition(model, id);
	}


	@PostMapping(value = SAVE_PREGUNTA_URL)
	public String save(@Validated @ModelAttribute(name = FORM_ATTRIBUTE) PreguntaBinariaForm formulario, BindingResult resultados) {
		return saving(formulario, resultados);
	}
	
	
	@PostMapping(value = DELETE_PREGUNTA_URL)
	public String delete(@ModelAttribute(name = FORM_ATTRIBUTE) DeletionForm formulario) {
		return deletion(formulario);
	}
	
	
	@Override
	protected PreguntaBinaria buildNewQuestion(PreguntaBinariaForm form) {
		return new PreguntaBinaria(form.getCodigo(), form.getPregunta(), form.getCategoria(), form.getCorrecta().getValor());
	}
}
