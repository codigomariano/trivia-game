package ar.com.codigomariano.controllers.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import ar.com.codigomariano.forms.RegistracionForm;
import ar.com.codigomariano.utilities.EntityHelper;

public class RegistracionAPIRestTest extends BaseAPIRestTest {

	
	@Test
	public void testRegistrarUsuario() {
		RegistracionForm form = new RegistracionForm();
		form.setEmail(EntityHelper.createRandomEmail());
		
		try {
		
			HttpRequest request = buildRequest(UsuarioAPI.REGISTER_USERS_URL, form);
								
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
			assertEquals(HttpStatus.CREATED.value(), response.statusCode());
			
		} catch (Exception e) {
			fail("No se pudo invocar la llamada a la API: " + e.getMessage());
		}	
	}
}
