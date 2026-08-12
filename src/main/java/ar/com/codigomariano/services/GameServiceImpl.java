package ar.com.codigomariano.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.codigomariano.dtos.DesafioDTO;
import ar.com.codigomariano.enums.PreguntaType;
import ar.com.codigomariano.helpers.Utils;

@Service
public class GameServiceImpl implements GameService {
	@Autowired
	private PreguntaBinariaService pBinariaService;
	@Autowired
	private PreguntaMultipleService pMultipleService;
	
	
	@Override
	public List<DesafioDTO> obtenerDesafiosParaPartida(int cantMaxima) {
		List<DesafioDTO> desafios = new ArrayList<DesafioDTO>();
		
		List<DesafioDTO> desafiosBinarios = pBinariaService.listarDesafios();
		
		List<DesafioDTO> desafiosMultiples = pMultipleService.listarDesafios();
		
		boolean hayPreguntas = !desafiosBinarios.isEmpty() || !desafiosMultiples.isEmpty();
		
		while(desafios.size() < cantMaxima && hayPreguntas) {
			PreguntaType tipoDesafio = Utils.obtenerTipoPregunta();
			
			switch (tipoDesafio) {
			case PreguntaType.BINARIA:
				sumarDesafioDesde(desafiosBinarios, desafios);
				break;
			case PreguntaType.MULTIPLE:
				sumarDesafioDesde(desafiosMultiples, desafios);
				break;
			}
			
			hayPreguntas = !desafiosBinarios.isEmpty() || !desafiosMultiples.isEmpty();
		}
		
		return desafios;
	}
	
	
	/**
	 * Obtiene aleatoriamente un desafío de la lista de origen y lo agrega a la lista de destino,
	 * siempre que la lista de origen contenga al menos un desafío.
	 */
	private void sumarDesafioDesde(List<DesafioDTO> desafiosOrigen, List<DesafioDTO> desafiosDestino) {
		int index = Utils.obtenerIndice(desafiosOrigen);
		
		if(index != -1) {
			DesafioDTO d = desafiosOrigen.remove(index);
			desafiosDestino.add(d);
		}
	}
}
