package ar.com.codigomariano.services;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.codigomariano.domain.Persistible;
import ar.com.codigomariano.repositories.BaseRepository;

public abstract class PersistableServiceImpl<T extends Persistible, R extends BaseRepository<T>> implements PersistableService {
	@Autowired
	private R repository;
	
	
	@Override
	public long cantidadEntidades() {
		return this.repository.count();
	}
	
	
	protected R repository() {
		return this.repository;
	}
}
