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
import org.springframework.transaction.annotation.Transactional;

import ar.com.codigomariano.dtos.UsuarioDTO;

public class UsuarioAPIRestTest extends SecuredAPIRestTest{

	
	@Test
	@Transactional
	public void testListUsuarios() {
		String token = login("test-api-admin@dummy.com.ar");
		
		HttpRequest request = buildRequest(UsuarioAPI.LIST_USERS_URL, token);
								
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
	@Transactional
	public void testListUsuariosWithNoAdmin() {
		String token = login("test-api@dummy.com.ar");
		
		HttpRequest request = buildRequest(UsuarioAPI.LIST_USERS_URL, token);
								
		try {
			
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
			assertEquals(HttpStatus.FORBIDDEN.value(), response.statusCode());
			
		} catch (Exception e) {
			fail("No se pudo invocar la llamada a la API: " + e.getMessage());
		}	
	}
}
