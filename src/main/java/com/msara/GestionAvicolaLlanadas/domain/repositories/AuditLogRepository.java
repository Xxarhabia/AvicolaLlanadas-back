package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.AuditLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface AuditLogRepository extends JpaRepository<AuditLogEntity, Long> {

    List<AuditLogEntity> findByTableNameAndRecordId(String tableName, Integer recordId);

    List<AuditLogEntity> findByChangedAtBetween(
            java.time.OffsetDateTime from, java.time.OffsetDateTime to);

    List<AuditLogEntity> findByAction(String action);
}
