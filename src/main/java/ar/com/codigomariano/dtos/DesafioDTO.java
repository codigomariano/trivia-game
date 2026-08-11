package ar.com.codigomariano.dtos;

import ar.com.codigomariano.enums.Categoria;

public class DesafioDTO extends BaseDTO {
	private Long id;
	private Categoria categoria;
	private String texto;
	private Integer puntos;
	private String[] opciones;
	private int indexCorrecto;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Integer getPuntos() {
		return puntos;
	}
	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}
	public String[] getOpciones() {
		return opciones;
	}
	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}
	public int getIndexCorrecto() {
		return indexCorrecto;
	}
	public void setIndexCorrecto(int indexCorrecto) {
		this.indexCorrecto = indexCorrecto;
	}
}
