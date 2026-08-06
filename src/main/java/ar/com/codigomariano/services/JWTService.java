package ar.com.codigomariano.services;

public interface JWTService extends Service {
	public String PREFIX_TOKEN = "Bearer";
	
	public String login(String email);
	
	public void buildAuthenticationContext(String token);
	
}
