package ar.com.codigomariano.domain;

import ar.com.codigomariano.forms.EntityForm;

public abstract class Editable<F extends EntityForm> extends Persistible {

	public abstract void copyPropertiesToForm(F form);
	
	public abstract void updatePropertiesFromForm(F form);
	
}
