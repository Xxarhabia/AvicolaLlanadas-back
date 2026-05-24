package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {
    Optional<FoodEntity> findByTypeFoodIgnoreCase(String typeFood);

    // Alimentos disponibles para venta al público
    List<FoodEntity> findByIsForSaleTrue();

    // Alimentos con stock por debajo del mínimo (para panel de alertas)
    @Query("SELECT f FROM FoodEntity f WHERE f.availableQuantity <= f.minStock")
    List<FoodEntity> findLowStock();

    boolean existsByTypeFoodIgnoreCase(String typeFood);
}
