package ar.com.codigomariano.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import ar.com.codigomariano.controllers.BaseController;
import ar.com.codigomariano.controllers.GameController;
import ar.com.codigomariano.controllers.HomeController;
import ar.com.codigomariano.controllers.LoginController;
import ar.com.codigomariano.controllers.RegistracionController;
import ar.com.codigomariano.controllers.admin.AdminController;
import ar.com.codigomariano.enums.Rol;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		return http
				.exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler()))
				.authorizeHttpRequests(auth -> auth
				.requestMatchers("/css/**", "/fonts/**", "/images/**", "/js/**").permitAll()
				.requestMatchers(BaseController.ROOT_URL).permitAll()
				.requestMatchers(HomeController.HOME_URL, GameController.GAME_AS_GUEST_URL).permitAll()
				.requestMatchers(LoginController.SIGN_IN_URL, LoginController.LOGIN_URL).permitAll()
				.requestMatchers(RegistracionController.SIGN_UP_URL, RegistracionController.REGISTRACION_URL).permitAll()
				.requestMatchers(AdminController.ADMIN_URL + "/**").hasRole(Rol.ADMINISTRADOR.name())
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
