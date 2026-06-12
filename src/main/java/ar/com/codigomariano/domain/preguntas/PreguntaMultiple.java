package ar.com.codigomariano.domain.preguntas;

import java.util.ArrayList;
import java.util.List;

import ar.com.codigomariano.domain.Respuesta;
import ar.com.codigomariano.enums.Categoria;

public class PreguntaMultiple extends Pregunta {
	private List<Respuesta> respuestas;
	
	
	public PreguntaMultiple(String codigo, String texto, Categoria categoria) {
		super(codigo, texto, categoria);
		this.respuestas = new ArrayList<Respuesta>();
	}	
}
