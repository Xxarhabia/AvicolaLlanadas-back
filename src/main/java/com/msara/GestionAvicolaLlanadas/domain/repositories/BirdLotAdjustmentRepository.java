package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.BirdLotAdjustmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BirdLotAdjustmentRepository extends JpaRepository<BirdLotAdjustmentEntity, Long> {

    List<BirdLotAdjustmentEntity> findByBirdLotLotId(Long lotId);

    List<BirdLotAdjustmentEntity> findByReason(String reason);
}
