package ar.com.codigomariano.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.codigomariano.domain.Persistible;

public interface BaseRepository<T extends Persistible> extends JpaRepository<T, Long> {

}
