package ar.com.codigomariano.services;

import java.util.List;

import ar.com.codigomariano.dtos.DesafioDTO;

public interface GameService extends Service {

	List<DesafioDTO> obtenerDesafiosParaPartida(int cantMaxima);
	
}
