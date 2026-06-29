package ar.com.codigomariano.helpers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.com.codigomariano.utilities.EntityHelper;

public class ValidationUtilsTest {
	
	
	@Test
	public void testValidEmailSuccess() {
		String email = EntityHelper.createRandomEmail();
		
		assertTrue(ValidationUtils.isValidEmail(email));
	}
	
	@Test
	public void testIsEmptyWhenNullSuccess() {
		assertTrue(ValidationUtils.isEmpty(null));
	}
	
	@Test
	public void testIsEmptyWhenBlankSuccess() {
		assertTrue(ValidationUtils.isEmpty(""));
	}
	
	@Test
	public void testIsEmptyWhenEmptySpacesSuccess() {
		assertTrue(ValidationUtils.isEmpty("   "));
	}
	
	@Test
	public void testTieneMasSuccess() {
		assertTrue(ValidationUtils.tieneMasDe("hola", 3));
	}
	
	
	@Test
	public void testTieneMenosSuccess() {
		assertTrue(ValidationUtils.tieneMenosDe("hola", 5));
	}
}
