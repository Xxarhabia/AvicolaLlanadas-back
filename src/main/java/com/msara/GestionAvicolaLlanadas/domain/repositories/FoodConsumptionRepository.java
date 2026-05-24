package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.FoodConsumptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FoodConsumptionRepository extends JpaRepository<FoodConsumptionEntity, Long> {

    List<FoodConsumptionEntity> findByBirdLotLotId(Long lotId);

    List<FoodConsumptionEntity> findByFoodFoodId(Long foodId);

    List<FoodConsumptionEntity> findByDateBetween(LocalDate from, LocalDate to);

    // Total consumido de un alimento (para tarjeta de dashboard)
    @Query("SELECT COALESCE(SUM(fc.quantityUsed), 0) FROM FoodConsumptionEntity fc WHERE fc.food.foodId = :foodId")
    Double totalConsumedByFood(@Param("foodId") Long foodId);

    // Consumo de un lote en un rango de fechas
    @Query("SELECT fc FROM FoodConsumptionEntity fc WHERE fc.birdLot.lotId = :lotId AND fc.date BETWEEN :from AND :to")
    List<FoodConsumptionEntity> findByLotAndDateRange(
            @Param("lotId") Long lotId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );
}
