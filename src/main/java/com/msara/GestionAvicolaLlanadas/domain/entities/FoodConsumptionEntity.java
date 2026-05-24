package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "food_consumption", schema = "avicola_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodConsumptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", referencedColumnName = "food_id", nullable = false)
    private FoodEntity food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bird_lot_id", referencedColumnName = "lot_id", nullable = false)
    private BirdLotEntity birdLot;

    @Column(name = "quantity_used", nullable = false, precision = 12, scale = 2)
    private BigDecimal quantityUsed;

    @Column(nullable = false, length = 10)
    private String unit;

    @Column(nullable = false)
    @Builder.Default
    private LocalDate date = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registered_by", referencedColumnName = "user_id")
    private UserEntity registeredBy;
}
