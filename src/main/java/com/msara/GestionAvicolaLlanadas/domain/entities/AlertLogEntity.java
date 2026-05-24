package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "alert_log", schema = "avicola_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private Long alertLId;

    @Column(name = "alert_type", nullable = false, length = 30)
    private String alertType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "reference_table", length = 50)
    private String referenceTable;

    @Column(name = "reference_id")
    private Integer referenceId;

    @Column(name = "is_resolved", nullable = false)
    @Builder.Default
    private Boolean isResolved = false;

    @Column(name = "create_at", nullable = false, updatable = false)
    @Builder.Default
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "resolved_at")
    private OffsetDateTime resolvedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resolved_by", referencedColumnName = "user_id")
    private UserEntity userEntity;

}
