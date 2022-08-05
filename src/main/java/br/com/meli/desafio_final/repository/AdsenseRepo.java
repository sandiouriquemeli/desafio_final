package br.com.meli.desafio_final.repository;

import br.com.meli.desafio_final.model.entity.Adsense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdsenseRepo extends CrudRepository<Adsense, Long> {
}
