package ar.com.codigomariano.domain;

import org.junit.jupiter.api.Test;

public abstract class BaseEntityTest<T extends Persistible> {

	@Test
	public void crearEntidadExitosa() {
		T entidad = crearEntidadValida();
		
		validarEntidadExitosa(entidad);
	}
	
	
	protected abstract T crearEntidadValida();
	
	protected abstract void validarEntidadExitosa(T entidad);
}
