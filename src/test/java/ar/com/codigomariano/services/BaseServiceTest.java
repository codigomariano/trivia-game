package ar.com.codigomariano.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.codigomariano.BaseContextTest;
import ar.com.codigomariano.domain.Editable;
import ar.com.codigomariano.forms.EntityForm;

public abstract class BaseServiceTest<T extends Editable<F>, F extends EntityForm, S extends CRUDService<T, F>> extends BaseContextTest {
	@Autowired
	private S servicio;
	
	
	@Test
	public void testGuardarEntidadExitosamente() {
		T entidad = crearEntidadValida();
		
		this.servicio.guardar(entidad);
	}
	
	
	protected abstract T crearEntidadValida();
	
	
	protected S servicio() {
		return this.servicio;
	}
}
