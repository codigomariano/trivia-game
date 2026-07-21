package ar.com.codigomariano.controllers.admin.preguntas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import ar.com.codigomariano.domain.preguntas.Pregunta;
import ar.com.codigomariano.forms.DeletionForm;
import ar.com.codigomariano.forms.PreguntaForm;
import ar.com.codigomariano.services.PreguntaService;
import ar.com.codigomariano.validators.PreguntaFormValidator;

public abstract class PreguntaAdminABMController<P extends Pregunta<F>, F extends PreguntaForm, S extends PreguntaService<P, F>, V extends PreguntaFormValidator<F>> extends PreguntaAdminController {	
	protected static final String EDITION_PREGUNTA = PREGUNTAS_URL  + "/edit";
	protected static final String SAVING_PREGUNTA = PREGUNTAS_URL + "/save";
	protected static final String DELETION_PREGUNTA = PREGUNTAS_URL +"/delete";
	
	private static final String FORM_PREGUNTAS_VIEW = PREGUNTAS_VIEW + "/form";
	
	@Autowired
	private S servicio;
	@Autowired
	private V validator;
	
	
	@InitBinder(value = FORM_ATTRIBUTE)
	void initFormValidator(WebDataBinder binder) {
		binder.addValidators(this.validator);
	}
	
	
	protected String edition(Model model, Long id) {
		F form = servicio().prepareEntity(id);
		
		model.addAttribute(FORM_ATTRIBUTE, form);
		
		return FORM_PREGUNTAS_VIEW;
	}
	
	
	protected String saving(F form, BindingResult resultados) {
		if(resultados.hasErrors()) return FORM_PREGUNTAS_VIEW;
		
		if(form.esCreacion()) {
			P entidad = buildNewQuestion(form);
			entidad.setPuntos(form.getPuntos());
			
			servicio().guardar(entidad);
		} else {
			servicio().actualizar(form);
		}
		
		return redirect(PreguntaAdminListController.LIST_PREGUNTAS_URL);
	}


	protected String deletion(DeletionForm form) {
		servicio().eliminar(form);
		
		return redirect(PreguntaAdminListController.LIST_PREGUNTAS_URL);
	}
	
	protected abstract P buildNewQuestion(F form);
	
	private S servicio() {
		return this.servicio;
	}
}
