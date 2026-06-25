package ar.com.codigomariano.domain.preguntas;

import ar.com.codigomariano.enums.Categoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "PREGUNTAS_BINARIAS")
public class PreguntaBinaria extends Pregunta {
	@Column(name = "OPCION_CORRECTA")
	private boolean correcta;
	
	
	// Sólo para Hibernate
	PreguntaBinaria() {
		super();
	}
	
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
