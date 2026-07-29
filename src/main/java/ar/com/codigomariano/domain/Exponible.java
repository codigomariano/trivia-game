package ar.com.codigomariano.domain;

import ar.com.codigomariano.dtos.DTO;
import ar.com.codigomariano.forms.EntityForm;

public abstract class Exponible<F extends EntityForm> extends Editable<F> {

	public abstract Class<? extends DTO> dtoForList();
	
	public abstract Class<? extends DTO> dtoForAPI();
	
}
