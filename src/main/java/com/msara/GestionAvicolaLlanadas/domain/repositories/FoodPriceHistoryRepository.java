package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.FoodPriceHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface FoodPriceHistoryRepository extends JpaRepository<FoodPriceHistoryEntity, Long> {

    List<FoodPriceHistoryEntity> findByFoodFoodId(Long foodId);

    // Precio vigente (effectiveTo nulo = precio actual)
    @Query("SELECT fph FROM FoodPriceHistoryEntity fph WHERE fph.food.foodId = :foodId AND fph.effectiveTo IS NULL")
    Optional<FoodPriceHistoryEntity> findCurrentPrice(@Param("foodId") Long foodId);
}
