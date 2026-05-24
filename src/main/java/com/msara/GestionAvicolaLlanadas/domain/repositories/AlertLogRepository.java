package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.AlertLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface AlertLogRepository extends JpaRepository<AlertLogEntity, Long> {

    // Todas las alertas no resueltas (para el panel de notificaciones)
    List<AlertLogEntity> findByIsResolvedFalseOrderByCreatedAtDesc();

    List<AlertLogEntity> findByAlertType(String alertType);

    List<AlertLogEntity> findByReferenceTableAndReferenceId(String table, Integer id);
}
