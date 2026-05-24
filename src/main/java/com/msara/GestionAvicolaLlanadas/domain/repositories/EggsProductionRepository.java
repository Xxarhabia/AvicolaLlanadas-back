package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.EggsProductionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EggsProductionRepository extends JpaRepository<EggsProductionEntity, Long> {

    List<EggsProductionEntity> findByBirdLotLotId(Long lotId);

    List<EggsProductionEntity> findByDateBetween(LocalDate from, LocalDate to);

    @Query("SELECT COALESCE(SUM(e.totalQuantity), 0) FROM EggsProductionEntity e WHERE e.birdLot.lotId = :lotId")
    Integer totalEggsByLot(@Param("lotId") Long lotId);
}
