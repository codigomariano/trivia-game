package ar.com.codigomariano.domain.preguntas;

import ar.com.codigomariano.domain.Editable;
import ar.com.codigomariano.enums.Categoria;
import ar.com.codigomariano.enums.PreguntaType;
import ar.com.codigomariano.forms.PreguntaForm;
import ar.com.codigomariano.helpers.ValidationUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "PREGUNTAS")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pregunta<F extends PreguntaForm> extends Editable<F>{
	public static final String ERR_CODIGO_OBLIGATORIO = "El código es obligatorio";
	public static final String ERR_CODIGO_MAX_LENGTH = "El código no puede superar los %d caracteres";
	public static final String ERR_TEXTO_OBLIGATORIO = "El username es obligatorio";
	public static final String ERR_TEXTO_MAX_LENGTH = "El nombre completo no puede superar los %d caracteres";
	public static final String ERR_CATEGORIA_OBLIGATORIO = "La categoría es obligatoria";
	public static final String ERR_PUNTOS_RANGE = "Los puntos deben estar entre %d y %d";
	
	public static final int CODIGO_MAX_LENGTH = 50;
	public static final int TEXTO_MAX_LENGTH = 250;
	public static final int DEFAULT_PUNTOS = 100;
	public static final int PUNTOS_MIN_VALUE = 1;
	public static final int PUNTOS_MAX_VALUE = 200;
	
	@Column(name = "CODIGO")
	private String codigo;
	
	@Column(name = "TEXTO")
	private String texto;
	
	@Column(name = "CATEGORIA")
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@Column(name = "PUNTOS")
	private int puntos;
	
	@Column(name = "ELIMINADA")
	private Boolean eliminada;
	
	
	// Sólo para Hibernate
	Pregunta() {
		super();
	}
	
	public Pregunta(String codigo, String texto, Categoria categoria) {
		this(codigo, texto, categoria, DEFAULT_PUNTOS);
	}
	
	public Pregunta(String codigo, String texto, Categoria categoria, int puntos) {
		setCodigo(codigo);
		setTexto(texto);
		setCategoria(categoria);
		setPuntos(puntos);
		setEliminada(Boolean.FALSE);
	}

	
	public Boolean estaActiva() {
		return ! Boolean.TRUE.equals(getEliminada());
	}
	
	@Override
	public void copyPropertiesToForm(F form) {
		form.setId(getId());
		form.setCodigo(getCodigo());
		form.setCategoria(getCategoria());
		form.setPregunta(getTexto());
		form.setPuntos(getPuntos());
	}
	
	@Override
	public void updatePropertiesFromForm(F form) {
		setCodigo(form.getCodigo());
		setCategoria(form.getCategoria());
		setTexto(form.getPregunta().trim());
		setPuntos(form.getPuntos());
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
		if(puntos < PUNTOS_MIN_VALUE || puntos > PUNTOS_MAX_VALUE) throw new IllegalArgumentException(String.format(ERR_PUNTOS_RANGE, PUNTOS_MIN_VALUE, PUNTOS_MAX_VALUE));
		this.puntos = puntos;
	}
	
	public Boolean getEliminada() {
		return eliminada;
	}

	public void setEliminada(Boolean eliminada) {
		this.eliminada = eliminada;
	}

	public abstract PreguntaType getType();
}
