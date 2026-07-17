package ar.com.codigomariano.services;

import ar.com.codigomariano.domain.Imagen;

public interface ImagenService extends PersistableService{

	public Imagen obtener(Long id);
	
}
