package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "food_consumption")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodConsumptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, name = "quantity_used")
    private Double quantityUsed;

    private String unit;

    @ManyToOne
    @JoinColumn(
            name = "bird_lot_id",
            referencedColumnName = "lot_id"
    )
    private BirdLotEntity birdLot;

    @ManyToOne
    @JoinColumn(
            name = "food_id",
            referencedColumnName = "food_id"
    )
    private FoodEntity food;
}
