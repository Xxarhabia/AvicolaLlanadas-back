package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "food")
@Builder
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long foodId;

    private String name;

    private String type;

    @Column(nullable = false, name = "available_quantity")
    private Double availableQuantity;

    @Column(nullable = false, name = "unit_measurement")
    private String unitMeasurement; //Unidad medida
}
