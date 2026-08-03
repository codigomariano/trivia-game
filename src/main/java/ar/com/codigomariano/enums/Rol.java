package ar.com.codigomariano.enums;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Rol {
	ADMINISTRADOR(new Permiso[] {Permiso.JUGAR_PARTIDA, Permiso.ADMINISTRAR_USUARIOS, Permiso.ADMINISTRAR_PREGUNTAS}),
	JUGADOR(new Permiso[] {Permiso.JUGAR_PARTIDA}),
	ARBITRO(new Permiso[] {Permiso.JUGAR_PARTIDA, Permiso.ADMINISTRAR_PREGUNTAS});
	
	private Permiso[] permisos;
	
	private Rol(Permiso[] permisos) {
		this.permisos = permisos;
	}
	
	public String getSecurityName() {
		return "ROLE_" + name();
	}
	
	public Collection<GrantedAuthority> getSecurityPermissions() {
		List<GrantedAuthority> permissions = new ArrayList<GrantedAuthority>();
		
		for (Permiso permiso : getPermisos()) {
			permissions.add(new SimpleGrantedAuthority(permiso.name()));
		}
		
		return permissions;
	}
	
	public Permiso[] getPermisos() {
		return this.permisos;
	}
	
	
}
