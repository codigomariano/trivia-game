package ar.com.codigomariano.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.com.codigomariano.enums.OpcionMultiple;
import ar.com.codigomariano.utilities.EntityHelper;

public class RespuestaEntityTest extends BaseEntityTest<Respuesta> {
	public static final String VALID_PREGUNTA_TEXT = "¿Cuál es la capital de Argentina?";
	
	@Override
	protected Respuesta crearEntidadValida() {
		OpcionMultiple op = EntityHelper.getRandomOpcionMultiple();
		return new Respuesta(op, VALID_PREGUNTA_TEXT);
	}

	@Override
	protected void validarEntidadExitosa(Respuesta entidad) {
		assertNotNull(entidad.getTexto());
		assertNotNull(entidad.getOpcion());
		assertNotNull(entidad.getCorrecta());
		assertFalse(entidad.getCorrecta());
		assertEquals(VALID_PREGUNTA_TEXT, entidad.getTexto());
	}

	@Test
	public void testCrearRespuestaCorrecta() {
		OpcionMultiple op = EntityHelper.getRandomOpcionMultiple();
		Respuesta r = new Respuesta(op, VALID_PREGUNTA_TEXT, Boolean.TRUE);
		
		assertTrue(r.getCorrecta());
	}
	
	@Test
	public void testCrearRespusetaWithNullName() {
		OpcionMultiple op = EntityHelper.getRandomOpcionMultiple();

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Respuesta(op, null));
		
		assertEquals(Respuesta.ERR_TEXTO_OBLIGATORIO, ex.getMessage());
	}
	
	@Test
	public void testCrearRespuestaWithEmptyName() {
		OpcionMultiple op = EntityHelper.getRandomOpcionMultiple();

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Respuesta(op, ""));
		
		assertEquals(Respuesta.ERR_TEXTO_OBLIGATORIO, ex.getMessage());
	}
	
	@Test
	public void testCrearRespuestaWithBlankName() {
		OpcionMultiple op = EntityHelper.getRandomOpcionMultiple();

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Respuesta(op, "    "));
		
		assertEquals(Respuesta.ERR_TEXTO_OBLIGATORIO, ex.getMessage());
	}
}
