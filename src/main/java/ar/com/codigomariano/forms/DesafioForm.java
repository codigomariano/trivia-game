package ar.com.codigomariano.forms;

public class DesafioForm {
	private Long idPregunta;
	private int indiceRespuestaElegia;
	
	
	public DesafioForm() {
		super();
	}
	
	public DesafioForm(Long idPregunta) {
		this.idPregunta = idPregunta;
	}
	
	public Long getIdPregunta() {
		return idPregunta;
	}
	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
	}
	public int getIndiceRespuestaElegia() {
		return indiceRespuestaElegia;
	}
	public void setIndiceRespuestaElegia(int indiceRespuestaElegia) {
		this.indiceRespuestaElegia = indiceRespuestaElegia;
	}
	
	
	
}
