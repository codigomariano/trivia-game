package ar.com.codigomariano.security.filters;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(existsJwtToken(request)) {
			String token = fetchToken(request);
		}

		filterChain.doFilter(request, response);
	}

	
	private boolean existsJwtToken(HttpServletRequest request) {
		return true;
	}
	
	private String fetchToken(HttpServletRequest request) {
		return null;
	}
}
