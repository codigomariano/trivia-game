package ar.com.codigomariano.security.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ar.com.codigomariano.services.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	@Autowired
	private JWTService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(existsJwtToken(request)) {
			String token = fetchToken(request);
			
			if(isValid(token)) {
				this.jwtService.buildAuthenticationContext(token);				
			} else {
				SecurityContextHolder.clearContext();
			}
			
		}

		filterChain.doFilter(request, response);
	}

	
	private boolean isValid(String token) {
		return this.jwtService.isValidToken(token);
	}
	
	
	private boolean existsJwtToken(HttpServletRequest request) {
		String header = fetchHeader(request);
		return header != null && header.startsWith(JWTService.PREFIX_TOKEN);
	}
	
	private String fetchToken(HttpServletRequest request) {
		String header = fetchHeader(request);
		
		String[] segmentos = header.split(" ");
		
		return segmentos[1];
	}
	
	private String fetchHeader(HttpServletRequest request) {
		return request.getHeader(HttpHeaders.AUTHORIZATION);
	}
}
