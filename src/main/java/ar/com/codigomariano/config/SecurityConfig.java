package ar.com.codigomariano.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import ar.com.codigomariano.controllers.BaseWebController;
import ar.com.codigomariano.controllers.GameController;
import ar.com.codigomariano.controllers.HomeController;
import ar.com.codigomariano.controllers.LoginController;
import ar.com.codigomariano.controllers.RegistracionController;
import ar.com.codigomariano.controllers.admin.AdminController;
import ar.com.codigomariano.controllers.rest.BaseAPIController;
import ar.com.codigomariano.enums.Rol;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Bean
	public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
		
		return http
				.securityMatcher(BaseAPIController.BASE_URL + "/**")
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(request -> request.anyRequest().authenticated())
				.csrf(csrf -> csrf.disable())
				.build();
		
	}
	
	@Bean
	public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
		
		return http
				.securityMatcher(BaseWebController.BASE_URL + "/**")
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
				.exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler()))
				.authorizeHttpRequests(auth -> auth
				.requestMatchers("/css/**", "/fonts/**", "/images/**", "/js/**").permitAll()
				.requestMatchers(BaseWebController.ROOT_URL).permitAll()
				.requestMatchers(HomeController.HOME_URL, GameController.GAME_AS_GUEST_URL).permitAll()
				.requestMatchers(LoginController.SIGN_IN_URL, LoginController.LOGIN_URL).permitAll()
				.requestMatchers(RegistracionController.SIGN_UP_URL, RegistracionController.REGISTRACION_URL).permitAll()
				.requestMatchers(AdminController.ADMIN_URL + "/**").hasAnyRole(Rol.ADMINISTRADOR.name(), Rol.ARBITRO.name())
				.anyRequest().authenticated())
				.formLogin(page -> page.loginPage(LoginController.SIGN_IN_URL))
				.build();
		
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
	    return (request, response, accessDeniedException) -> {
	        response.sendRedirect(LoginController.SIGN_IN_URL);
	    };
	}
}
