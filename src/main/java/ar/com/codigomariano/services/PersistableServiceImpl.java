package ar.com.codigomariano.services;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.codigomariano.domain.Persistible;
import ar.com.codigomariano.repositories.BaseRepository;

public abstract class PersistableServiceImpl<T extends Persistible, R extends BaseRepository<T>> implements PersistableService {
	@Autowired
	private R repository;
	
	
	
	protected R repository() {
		return this.repository;
	}
}
