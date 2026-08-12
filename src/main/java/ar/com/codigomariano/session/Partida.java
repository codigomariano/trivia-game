package ar.com.codigomariano.session;

import java.util.List;
import java.util.Optional;

import ar.com.codigomariano.dtos.DesafioDTO;
import ar.com.codigomariano.exceptions.NoMoreDesafiosException;

public class Partida {
	private static final int MIN_INDEX = 0;
	private List<DesafioDTO> desafios;
	private Integer puntos;
	private int desafioActual;
	
	public Partida(List<DesafioDTO> desafios) {
		this.desafios = desafios;
		this.puntos = 0;
		this.desafioActual = MIN_INDEX;
	}

	
	public void actualizarPuntos(Long idPregunta, int indexSeleccionado) {
		Optional<DesafioDTO> resultado = this.desafios
								.stream()
								.filter(d -> d.getId().equals(idPregunta))
								.findFirst();
		
		if(resultado.isPresent()) {
			DesafioDTO desafio = resultado.get();
			if(desafio.esRespuestaCorrecta(indexSeleccionado)) {
				this.puntos = this.puntos + desafio.getPuntos();
			}
		}
	}
	
	public DesafioDTO siguienteDesafio() {
		DesafioDTO desafio = null;
		
		if(desafioActual < this.desafios.size()) {
			
			desafio = this.desafios.get(desafioActual);
			this.desafioActual++;
			return desafio;
			
		} throw new NoMoreDesafiosException();
	}
	
	public Integer getPuntos() {
		return puntos;
	}
}
