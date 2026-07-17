package ar.com.codigomariano.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.com.codigomariano.domain.Imagen;
import ar.com.codigomariano.repositories.ImagenRepository;

@Service
public class ImagenServiceImpl extends PersistableServiceImpl<Imagen, ImagenRepository> implements ImagenService {

	
	@Override
	public Imagen obtener(Long id) {
		Optional<Imagen> imagen = repository().findById(id);
		
		return imagen.isPresent() ? imagen.get() : null;
	}
}
