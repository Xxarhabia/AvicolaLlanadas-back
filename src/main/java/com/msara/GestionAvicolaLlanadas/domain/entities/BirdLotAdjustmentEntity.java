package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "bird_lot_adjustment", schema = "avicola_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdLotAdjustmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adj_id")
    private Long adjId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bird_lot_id", referencedColumnName = "lot_id", nullable = false)
    private BirdLotEntity birdLot;

    // Positivo = ingreso de aves, negativo = salida (venta/muerte excepcional)
    @Column(name = "quantity_delta", nullable = false)
    private Integer quantityDelta;

    // VENTA | MUERTE | INGRESO | TRASLADO | AJUSTE
    @Column(nullable = false, length = 20)
    private String reason;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adjusted_by", referencedColumnName = "user_id")
    private UserEntity adjustedBy;

    @Column(name = "adjusted_at", nullable = false)
    @Builder.Default
    private OffsetDateTime adjustedAt = OffsetDateTime.now();
}
