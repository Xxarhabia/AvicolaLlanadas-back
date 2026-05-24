package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.BirdLotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BirdLotRepository extends JpaRepository<BirdLotEntity, Long> {

    List<BirdLotEntity> findByStatus(String status);

    // Lotes activos ordenados por fecha de ingreso
    List<BirdLotEntity> findByStatusOrderByDateEntryDesc(String status);

    // Lotes cuyo current_quantity llegó a 0 (candidatos a cerrar)
    List<BirdLotEntity> findByCurrentQuantityAndStatus(Integer quantity, String status);

    // Buscar por tipo de ave
    List<BirdLotEntity> findByBirdTypeContainingIgnoreCase(String birdType);
}
