package ar.com.codigomariano.controllers.rest;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.util.HashMap;
import java.util.Map;

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
	
	
	protected HttpRequest buildRequest(String forPath) {
		Map<String, String> headers = new HashMap<String, String>();
		
		headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		return buildRequest(forPath, headers);
	}
	
	/**
	 * Crea una solicitud HTTP de tipo GET lista para ser enviada
	 * mediante un cliente HTTP
	 */
	protected HttpRequest buildRequest(String forPath, Map<String, String> headers) {
		URI uri = buildURLForPath(forPath);
		
		Builder req = HttpRequest.newBuilder(uri).GET();
		for (String key : headers.keySet()) {
			req.header(key, headers.get(key));
		}
		
		return req.build();
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
