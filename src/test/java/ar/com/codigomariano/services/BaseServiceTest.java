package ar.com.codigomariano.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ar.com.codigomariano.domain.Editable;
import ar.com.codigomariano.forms.EntityForm;

@SpringBootTest
@ActiveProfiles(value = "test")
public abstract class BaseServiceTest<T extends Editable<F>, F extends EntityForm, S extends CRUDService<T, F>> {
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
