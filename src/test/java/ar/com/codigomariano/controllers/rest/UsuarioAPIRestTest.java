package ar.com.codigomariano.controllers.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import ar.com.codigomariano.dtos.UsuarioDTO;
import ar.com.codigomariano.forms.RegistracionForm;
import ar.com.codigomariano.utilities.EntityHelper;

public class UsuarioAPIRestTest extends BaseAPIRestTest{

	
	@Test
	public void testListUsuarios() {
		HttpRequest request = buildRequest(UsuarioAPI.LIST_USERS_URL);
								
		try {
			
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
			List<UsuarioDTO> usuarios = mapper().
					readerForListOf(UsuarioDTO.class)
					.readValue(response.body());
			
			assertFalse(usuarios.isEmpty());
			for (UsuarioDTO dto : usuarios) {
				assertNotNull(dto.getEmail());
			}
			
		} catch (Exception e) {
			fail("No se pudo invocar la llamada a la API: " + e.getMessage());
		}	
	}
	
	
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
