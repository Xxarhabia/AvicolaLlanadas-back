package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.StockAdjustmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface StockAdjustmentRepository extends JpaRepository<StockAdjustmentEntity, Long> {

    List<StockAdjustmentEntity> findByFoodFoodId(Long foodId);

    List<StockAdjustmentEntity> findByReason(String reason);

    List<StockAdjustmentEntity> findByAdjustedAtBetween(
            java.time.OffsetDateTime from, java.time.OffsetDateTime to);
}
