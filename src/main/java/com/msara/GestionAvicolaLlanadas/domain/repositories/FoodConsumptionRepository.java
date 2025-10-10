package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.FoodConsumptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodConsumptionRepository extends JpaRepository<FoodConsumptionEntity, Long> {
}
