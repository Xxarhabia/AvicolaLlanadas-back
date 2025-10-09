package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.BirdLotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BirdLotRepository extends JpaRepository<BirdLotEntity, Long> {

    List<BirdLotEntity> findAllByStatus(int status);
}
