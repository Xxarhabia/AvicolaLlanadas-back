package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.MortalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
interface MortalityRepository extends JpaRepository<MortalityEntity, Long> {

    List<MortalityEntity> findByBirdLotLotId(Long lotId);

    List<MortalityEntity> findByDateBetween(LocalDate from, LocalDate to);

    // Total de bajas de un lote
    @Query("SELECT COALESCE(SUM(m.numberCasualties), 0) FROM MortalityEntity m WHERE m.birdLot.lotId = :lotId")
    Integer totalCasualtiesByLot(@Param("lotId") Long lotId);
}
