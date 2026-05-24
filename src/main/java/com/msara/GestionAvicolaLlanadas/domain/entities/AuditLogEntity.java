package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "audit_log", schema = "avicola_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    @Column(name = "table_name", nullable = false, length = 50)
    private String tableName;

    @Column(name = "record_id")
    private Integer recordId;

    // INSERT | UPDATE | DELETE
    @Column(nullable = false, length = 10)
    private String action;

    // Datos anteriores en JSON (nulo en INSERT)
    @Column(name = "old_data", columnDefinition = "jsonb")
    private String oldData;

    // Datos nuevos en JSON (nulo en DELETE)
    @Column(name = "new_data", columnDefinition = "jsonb")
    private String newData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "changed_by", referencedColumnName = "user_id")
    private UserEntity changedBy;

    @Column(name = "changed_at", nullable = false, updatable = false)
    @Builder.Default
    private OffsetDateTime changedAt = OffsetDateTime.now();

    // Dirección IP como String; PostgreSQL lo almacena como INET
    @Column(name = "ip_address", columnDefinition = "inet")
    private String ipAddress;
}
