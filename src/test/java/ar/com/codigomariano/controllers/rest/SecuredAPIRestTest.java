package ar.com.codigomariano.controllers.rest;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import ar.com.codigomariano.services.JWTService;

public abstract class SecuredAPIRestTest extends BaseAPIRestTest {
	@Autowired
	private JWTService jwtService;
	
	
	protected HttpRequest buildRequest(String forPath, String token) {
		Map<String, String> headers = new HashMap<String, String>();
		
		headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.put(HttpHeaders.AUTHORIZATION, JWTService.PREFIX_TOKEN + " " + token);
		
		return super.buildRequest(forPath, headers);
	}
	
	
	protected String login(String email) {
		return this.jwtService.login(email);
	}
}
