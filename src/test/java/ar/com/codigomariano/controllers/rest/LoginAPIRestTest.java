package ar.com.codigomariano.controllers.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import ar.com.codigomariano.forms.LoginForm;
import ar.com.codigomariano.utilities.EntityHelper;

public class LoginAPIRestTest extends BaseAPIRestTest {

	@Test
	public void testLogin() {
		LoginForm form = new LoginForm();
		form.setEmail(EntityHelper.createRandomEmail());
		
		try {
		
			HttpRequest request = buildRequest(UsuarioAPI.REGISTER_USERS_URL, form);
								
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
			assertEquals(HttpStatus.CREATED.value(), response.statusCode());
			
			assertNotNull(response.body());
			
		} catch (Exception e) {
			fail("No se pudo invocar la llamada a la API: " + e.getMessage());
		}	
	}
}
