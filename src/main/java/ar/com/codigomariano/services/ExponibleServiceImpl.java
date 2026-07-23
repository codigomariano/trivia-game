package ar.com.codigomariano.services;

import java.util.List;
import java.util.stream.Collectors;

import ar.com.codigomariano.domain.Exponible;
import ar.com.codigomariano.dtos.DTO;
import ar.com.codigomariano.forms.EntityForm;
import ar.com.codigomariano.repositories.BaseRepository;

public abstract class ExponibleServiceImpl<E extends Exponible<F, D>, F extends EntityForm, R extends BaseRepository<E>, D extends DTO> extends CRUDServiceImpl<E, F, R> implements ExponibleService<E, F, D> {

	@Override
	public List<D> exponer() {
		List<E> entities = listAll();
		
		List<D> dtos = entities.stream().map(e -> e.asDTO()).collect(Collectors.toList());
		
		return dtos;
	}
}
