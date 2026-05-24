package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.MedicineEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository {

    Optional<MedicineEntity> findByNameIgnoreCase(String name);

    // Medicamentos que vencen en los próximos N días
    @Query("SELECT m FROM MedicineEntity m WHERE m.expiryDate IS NOT NULL AND m.expiryDate <= :fecha")
    Optional<MedicineEntity> findByExpiryDateBefore(@Param("fecha") LocalDate fecha);

    // Medicamentos con stock bajo
    @Query("SELECT m FROM MedicineEntity m WHERE m.availableQuantity <= m.minStock")
    List<MedicineEntity> findLowStock();
}
