package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.UsedFoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedFoodRepository extends JpaRepository<UsedFoodEntity, Long> {
}
