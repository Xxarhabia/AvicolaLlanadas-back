package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "food_price_history", schema = "avicola_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodPriceHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Long priceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", referencedColumnName = "food_id", nullable = false)
    private FoodEntity food;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "effective_from", nullable = false)
    @Builder.Default
    private LocalDate effectiveFrom = LocalDate.now();

    // Nulo = precio vigente actualmente
    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registered_by", referencedColumnName = "user_id")
    private UserEntity registeredBy;

    @Column(columnDefinition = "TEXT")
    private String notes;
}