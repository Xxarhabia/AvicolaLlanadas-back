package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "used_food")
@Builder
public class UsedFoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "used_food_id")
    private Long usedFoodId;

    @Column(name = "sold_to_client")
    private int soldToClient;

    @Column(name = "used_in_bird")
    private int usedInBird;

    @Column(name = "date_use")
    private Date dateUse;

    @ManyToOne
    @JoinColumn(name = "food_id", referencedColumnName = "food_id")
    private FoodEntity food;
}
