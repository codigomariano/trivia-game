package ar.com.codigomariano.domain.preguntas;

import ar.com.codigomariano.domain.Persistible;
import ar.com.codigomariano.enums.Categoria;

public abstract class Pregunta extends Persistible {
	protected static final int DEFAULT_PUNTOS = 100;
	private String codigo;
	private String texto;
	private Categoria categoria;
	private int puntos;
	
	
	public Pregunta(String codigo, String texto, Categoria categoria) {
		this(codigo, texto, categoria, DEFAULT_PUNTOS);
	}
	
	public Pregunta(String codigo, String texto, Categoria categoria, int puntos) {
		this.codigo = codigo;
		this.texto = texto;
		this.categoria = categoria;
		this.puntos = puntos;
	}
}
