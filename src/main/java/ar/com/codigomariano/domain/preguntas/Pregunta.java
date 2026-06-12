package ar.com.codigomariano.domain.preguntas;

import ar.com.codigomariano.domain.Persistible;
import ar.com.codigomariano.enums.Categoria;
import ar.com.codigomariano.helpers.ValidationUtils;

public abstract class Pregunta extends Persistible {
	public static final String ERR_CODIGO_OBLIGATORIO = "El código es obligatorio";
	public static final String ERR_CODIGO_MAX_LENGTH = "El código no puede superar los %d caracteres";
	public static final String ERR_TEXTO_OBLIGATORIO = "El username es obligatorio";
	public static final String ERR_TEXTO_MAX_LENGTH = "El nombre completo no puede superar los %d caracteres";
	public static final String ERR_CATEGORIA_OBLIGATORIO = "La categoría es obligatoria";

	public static final int CODIGO_MAX_LENGTH = 50;
	public static final int TEXTO_MAX_LENGTH = 250;
	public static final int DEFAULT_PUNTOS = 100;
	
	private String codigo;
	private String texto;
	private Categoria categoria;
	private int puntos;
	
	
	public Pregunta(String codigo, String texto, Categoria categoria) {
		this(codigo, texto, categoria, DEFAULT_PUNTOS);
	}
	
	public Pregunta(String codigo, String texto, Categoria categoria, int puntos) {
		setCodigo(codigo);
		setTexto(texto);
		setCategoria(categoria);
		setPuntos(puntos);
	}

	public String getCodigo() {
		return codigo;
	}

	public String getTexto() {
		return texto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public int getPuntos() {
		return puntos;
	}
	
	public void setCodigo(String codigo) {
		if(ValidationUtils.isEmpty(codigo)) {
			throw new IllegalArgumentException(ERR_CODIGO_OBLIGATORIO);
		}else if(ValidationUtils.tieneMasDe(codigo, CODIGO_MAX_LENGTH)) {
			throw new IllegalArgumentException(String.format(ERR_CODIGO_MAX_LENGTH, CODIGO_MAX_LENGTH));
		}
		this.codigo = codigo;
	}
	
	public void setTexto(String texto) {
		if(ValidationUtils.isEmpty(texto)) {
			throw new IllegalArgumentException(ERR_TEXTO_OBLIGATORIO);
		}else if(ValidationUtils.tieneMasDe(texto, TEXTO_MAX_LENGTH)) {
			throw new IllegalArgumentException(String.format(ERR_TEXTO_MAX_LENGTH, TEXTO_MAX_LENGTH));
		}
		this.texto = texto;
	}
	
	public void setCategoria(Categoria categoria) {
		if(categoria == null) throw new IllegalArgumentException(ERR_CATEGORIA_OBLIGATORIO);
		this.categoria = categoria;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
}
