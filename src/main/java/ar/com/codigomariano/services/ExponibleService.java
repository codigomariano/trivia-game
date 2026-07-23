package ar.com.codigomariano.services;

import java.util.List;

import ar.com.codigomariano.domain.Exponible;
import ar.com.codigomariano.dtos.DTO;
import ar.com.codigomariano.forms.EntityForm;

public interface ExponibleService<E extends Exponible<F, D>, F extends EntityForm, D extends DTO> extends CRUDService<E, F> {

	List<D> exponer();
	
}
