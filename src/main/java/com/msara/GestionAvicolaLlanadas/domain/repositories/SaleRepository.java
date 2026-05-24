package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    List<SaleEntity> findByClientClientId(Long clientId);

    List<SaleEntity> findBySaleType(String saleType);

    List<SaleEntity> findByStatus(String status);

    // Ventas en un rango de fechas
    @Query("SELECT s FROM SaleEntity s WHERE CAST(s.date AS LocalDate) BETWEEN :from AND :to")
    List<SaleEntity> findByDateRange(@Param("from") LocalDate from, @Param("to") LocalDate to);

    // Total vendido en el período
    @Query("SELECT COALESCE(SUM(s.totalAmount), 0) FROM SaleEntity s WHERE s.status = 'COMPLETADA' AND CAST(s.date AS LocalDate) BETWEEN :from AND :to")
    Double totalSalesInPeriod(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
