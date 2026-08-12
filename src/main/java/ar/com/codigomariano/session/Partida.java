package ar.com.codigomariano.session;

import java.util.List;

import ar.com.codigomariano.dtos.DesafioDTO;

public class Partida {
	private List<DesafioDTO> desafios;
	private Integer puntos;
	
	
	public Partida(List<DesafioDTO> desafios) {
		this.desafios = desafios;
		this.puntos = 0;
	}

	public Integer getPuntos() {
		return puntos;
	}
}
