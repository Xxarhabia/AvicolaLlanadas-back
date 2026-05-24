package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "stock_adjustment", schema = "avicola_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAdjustmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adj_id")
    private Long adjId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", referencedColumnName = "food_id", nullable = false)
    private FoodEntity food;

    // Positivo = entrada, negativo = salida/merma
    @Column(name = "quantity_delta", nullable = false, precision = 12, scale = 2)
    private BigDecimal quantityDelta;

    // COMPRA | MERMA | DEVOLUCION | AJUSTE | VENCIMIENTO
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
