package ar.com.codigomariano.domain;

import ar.com.codigomariano.dtos.DTO;
import ar.com.codigomariano.forms.EntityForm;

public abstract class Exponible<F extends EntityForm, D extends DTO> extends Editable<F> {

	public abstract D asDTO();
	
}
