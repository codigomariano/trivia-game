package ar.com.codigomariano.enums;

public enum Rol {
	ADMINISTRADOR(new Permiso[] {Permiso.JUGAR_PARTIDA, Permiso.ADMINISTRAR_USUARIOS, Permiso.ADMINISTRAR_PREGUNTAS}),
	JUGADOR(new Permiso[] {Permiso.JUGAR_PARTIDA});
	
	private Permiso[] permisos;
	
	private Rol(Permiso[] permisos) {
		this.permisos = permisos;
	}
	
	public String getSecurityName() {
		return "ROLE_" + name();
	}
	
	public Permiso[] getPermisos() {
		return this.permisos;
	}
	
	
}
