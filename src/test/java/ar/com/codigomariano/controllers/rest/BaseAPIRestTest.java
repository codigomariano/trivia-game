package ar.com.codigomariano.controllers.rest;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.codigomariano.BaseContextTest;

public abstract class BaseAPIRestTest extends BaseContextTest {
	@Autowired
	private ObjectMapper mapper;
	
	
	
	/**
	 * Genera la URI completa de un endpoint utilizando el servidor
	 * local y el puerto en el que Spring levanta el servidor 
	 * durante la ejecución de los tests 
	 */
	protected URI buildURLForPath(String path) {
		return URI.create(LOCAL_SERVER + ":" + puerto() + path);
	}
	
	
	/**
	 * Crea una solicitud HTTP de tipo GET lista para ser enviada
	 * mediante un cliente HTTP
	 */
	protected HttpRequest buildRequest(String forPath) {
		URI uri = buildURLForPath(forPath);
		
		return HttpRequest
				.newBuilder(uri)
				.GET()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}
	
	/**
	 * Crea una solicitud HTTP de tipo POST lista para ser enviada
	 * mediante un cliente HTTP
	 * @throws JsonProcessingException 
	 */
	protected HttpRequest buildRequest(String forPath, Object form) throws JsonProcessingException {
		String jsonBody = mapper().writeValueAsString(form);
		
		return HttpRequest
						.newBuilder(buildURLForPath(forPath))
						.POST(BodyPublishers.ofString(jsonBody))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.build();
	}
	
	
	protected ObjectMapper mapper() {
		return this.mapper;
	}
}
