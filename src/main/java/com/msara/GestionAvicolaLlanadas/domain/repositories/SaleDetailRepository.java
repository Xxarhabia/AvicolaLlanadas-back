package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.SaleDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetailEntity, Long> {

    List<SaleDetailEntity> findBySaleSaleId(Long saleId);

    List<SaleDetailEntity> findByFoodFoodId(Long foodId);

    List<SaleDetailEntity> findByBirdLotLotId(Long lotId);
}
