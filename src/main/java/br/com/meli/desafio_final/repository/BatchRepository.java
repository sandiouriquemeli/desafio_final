package br.com.meli.desafio_final.repository;

import br.com.meli.desafio_final.model.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findAllByAdsenseId(Long adsenseId);
}