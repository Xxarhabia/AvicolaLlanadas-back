package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.MedicationApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface MedicationApplicationRepository extends JpaRepository<MedicationApplicationEntity, Long> {

    List<MedicationApplicationEntity> findByBirdLotLotId(Long lotId);

    List<MedicationApplicationEntity> findByMedicineMedicineId(Long medicineId);
}
