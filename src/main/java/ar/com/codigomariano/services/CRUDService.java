package ar.com.codigomariano.services;

import java.util.List;

import ar.com.codigomariano.domain.Editable;
import ar.com.codigomariano.forms.DeletionForm;
import ar.com.codigomariano.forms.EntityForm;

public interface CRUDService<E extends Editable<F>, F extends EntityForm> extends PersistableService{

	public List<E> listAll();
	
	public void guardar(E entidad);
	
	public void actualizar(F form);
	
	public F prepareEntity(Long id);
	
	public void eliminar(DeletionForm form);
	
}
