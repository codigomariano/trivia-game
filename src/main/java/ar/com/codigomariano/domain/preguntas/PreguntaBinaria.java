package ar.com.codigomariano.domain.preguntas;

import ar.com.codigomariano.enums.Categoria;

public class PreguntaBinaria extends Pregunta {
	private boolean correcta;
	
	
	public PreguntaBinaria(String codigo, String texto, Categoria categoria) {
		this(codigo, texto, categoria, false);
	}
	
	public PreguntaBinaria(String codigo, String texto, Categoria categoria, boolean correcta) {
		super(codigo, texto, categoria);
		this.correcta = correcta;
	}

	public boolean isCorrecta() {
		return correcta;
	}

	public void setCorrecta(boolean correcta) {
		this.correcta = correcta;
	}
}
