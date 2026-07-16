package ar.com.codigomariano.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.codigomariano.domain.Imagen;
import ar.com.codigomariano.repositories.ImagenRepository;

@Service
public class ImagenServiceImpl implements ImagenService {
	@Autowired
	private ImagenRepository repositorio;
	
	@Override
	public Imagen obtener(Long id) {
		Optional<Imagen> imagen = this.repositorio.findById(id);
		
		return imagen.isPresent() ? imagen.get() : null;
	}
}
