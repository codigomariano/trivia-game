package ar.com.codigomariano.domain.interfaces;

import ar.com.codigomariano.forms.EntityForm;

public interface Editable<F extends EntityForm> {

	void copyPropertiesToForm(F form);
	
	void updatePropertiesFromForm(F form);
	
}
