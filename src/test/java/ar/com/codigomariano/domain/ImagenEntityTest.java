package ar.com.codigomariano.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.com.codigomariano.helpers.ValidationUtils;
import ar.com.codigomariano.utilities.EntityHelper;

public class ImagenEntityTest extends BaseEntityTest<Imagen> {

	@Override
	protected Imagen crearEntidadValida() {
		byte[] imagen = EntityHelper.readFileFromResource(EntityHelper.EXISTING_PROFILE_IMG);
		return new Imagen(EntityHelper.VALID_PROFILE_IMG_NAME, null, imagen);
	}

	@Override
	protected void validarEntidadExitosa(Imagen entidad) {
		assertNotNull(entidad.getNombre());
		assertTrue(ValidationUtils.tieneMenosDe(entidad.getNombre(), Imagen.NOMBRE_MAX_LENGTH));
		assertNotNull(entidad.getContenido());
	}
	
	@Test
	public void testCrearImagenWithNullName() {
		byte[] content = EntityHelper.readFileFromResource(EntityHelper.EXISTING_PROFILE_IMG);
		
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Imagen(null, null, content));
		
		assertEquals(Imagen.ERR_NOMBRE_OBLIGATORIO, ex.getMessage());
	}
	
	@Test
	public void testCrearImagenWithEmptyName() {
		byte[] content = EntityHelper.readFileFromResource(EntityHelper.EXISTING_PROFILE_IMG);
		
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Imagen("", null, content));
		
		assertEquals(Imagen.ERR_NOMBRE_OBLIGATORIO, ex.getMessage());
	}
	
	@Test
	public void testCrearImagenWithBlankName() {
		byte[] content = EntityHelper.readFileFromResource(EntityHelper.EXISTING_PROFILE_IMG);
		
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Imagen("   ", null, content));
		
		assertEquals(Imagen.ERR_NOMBRE_OBLIGATORIO, ex.getMessage());
	}
	
	@Test
	public void testCrearImagenWithNullContent() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Imagen(EntityHelper.VALID_PROFILE_IMG_NAME, null, null));
		
		assertEquals(Imagen.ERR_CONTENIDO_OBLIGATORIO, ex.getMessage());
	}
}
