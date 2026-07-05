package ar.com.codigomariano.services;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.codigomariano.domain.Persistible;
import ar.com.codigomariano.repositories.BaseRepository;

public abstract class CRUDServiceImpl<T extends Persistible, R extends BaseRepository<T>> implements CRUDSevice<T> {
	@Autowired
	private R repository;
	
	
	@Override
	public void guardar(T entidad) {
		this.repository.save(entidad);
	}
	
	
	protected R repository() {
		return this.repository;
	}
}
