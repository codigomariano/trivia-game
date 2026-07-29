package ar.com.codigomariano.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import ar.com.codigomariano.domain.Exponible;
import ar.com.codigomariano.dtos.DTO;
import ar.com.codigomariano.forms.EntityForm;
import ar.com.codigomariano.repositories.BaseRepository;

public abstract class ExponibleServiceImpl<E extends Exponible<F>, F extends EntityForm, R extends BaseRepository<E>, D extends DTO> extends CRUDServiceImpl<E, F, R> implements ExponibleService<E, F, D> {
	private ModelMapper mapper = new ModelMapper();
	
	
	@Override
	public List<DTO> list() {
		List<E> entities = listAll();
		
		List<DTO> dtos = entities.stream()
				.map(e -> mapper.map(e, e.dtoForList()))
				.collect(Collectors.toList());
		
		return dtos;
	}
	
	@Override
	public List<DTO> exponer() {
		List<E> entities = listAll();
		
		List<DTO> dtos = entities.stream()
				.map(e -> mapper.map(e, e.dtoForAPI()))
				.collect(Collectors.toList());
		
		return dtos;
	}
}
