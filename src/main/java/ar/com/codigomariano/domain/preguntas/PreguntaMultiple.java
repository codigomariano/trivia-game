package ar.com.codigomariano.domain.preguntas;

import java.util.ArrayList;
import java.util.List;

import ar.com.codigomariano.domain.Respuesta;
import ar.com.codigomariano.enums.Categoria;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PREGUNTAS_MULTIPLES")
public class PreguntaMultiple extends Pregunta {
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "PREGUNTA_ID", referencedColumnName = "ID", nullable = false)
	private List<Respuesta> respuestas;
	
	
	// Sólo para Hibernate
	PreguntaMultiple() {
		super();
	}
	
	public PreguntaMultiple(String codigo, String texto, Categoria categoria) {
		super(codigo, texto, categoria);
		this.respuestas = new ArrayList<Respuesta>();
	}	
}
