package ar.com.codigomariano.services;

import java.util.List;
import java.util.Optional;

import ar.com.codigomariano.domain.Editable;
import ar.com.codigomariano.forms.DeletionForm;
import ar.com.codigomariano.forms.EntityForm;
import ar.com.codigomariano.helpers.ValidationUtils;
import ar.com.codigomariano.repositories.BaseRepository;

public abstract class CRUDServiceImpl<E extends Editable<F>, F extends EntityForm, R extends BaseRepository<E>> extends PersistableServiceImpl<E, R> implements CRUDService<E, F> {

	
	@Override
	public List<E> listAll() {
		return repository().findAll();
	}
	
	@Override
	public void guardar(E entidad) {
		repository().save(entidad);
	}
	
	@Override
	public F prepareEntity(Long id) {
		F form = emptyForm();
		
		if(ValidationUtils.isValidID(id)) {
			Optional<E> entity = repository().findById(id);
			if(entity.isPresent()) entity.get().copyPropertiesToForm(form);
		}
		
		return form;
	}
	
	@Override
	public void eliminar(DeletionForm form) {
		if(ValidationUtils.isValidID(form.getId())) {
			repository().deleteById(form.getId());
		}
	}
	
	protected abstract F emptyForm();
}
