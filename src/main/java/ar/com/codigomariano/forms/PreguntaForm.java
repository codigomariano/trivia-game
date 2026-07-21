package ar.com.codigomariano.forms;

import ar.com.codigomariano.enums.Categoria;
import ar.com.codigomariano.enums.Opcion;
import ar.com.codigomariano.enums.PreguntaType;

public abstract class PreguntaForm extends EntityForm{
	private PreguntaType type;
	private String codigo;
	private String pregunta;
	private Categoria categoria;
	private Integer puntos;
	
	
	public PreguntaForm() {
		super();
	}
	
	public PreguntaForm(PreguntaType type) {
		this.type = type;
	}
	
	
	public boolean esBinaria() {
		return PreguntaType.BINARIA.equals(type);
	}
	
	public boolean esMultiple() {
		return PreguntaType.MULTIPLE.equals(type);
	}

	public Categoria[] getCategorias() {
		return Categoria.values();
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public PreguntaType getType() {
		return type;
	}

	public void setType(PreguntaType type) {
		this.type = type;
	}
	
	public String getPregunta() {
		return pregunta;
	}
	
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}	
	
	public abstract Opcion[] getOpciones();
}
