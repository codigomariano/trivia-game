package ar.com.codigomariano.services;

import ar.com.codigomariano.domain.Persistible;

public interface CRUDSevice<T extends Persistible> {

	public void guardar(T entidad);
	
}
