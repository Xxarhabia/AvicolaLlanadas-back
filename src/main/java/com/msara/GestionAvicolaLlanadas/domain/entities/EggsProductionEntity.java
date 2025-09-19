package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "eggs_production")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EggsProductionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eggs_prod_id")
    private Long eggsProductionId;

    @Column(nullable = false)
    private Date date;

    @Column(name = "total_quantity", nullable = false)
    private int totalQuantity;

    @ManyToOne
    @JoinColumn(
            name = "birt_lot_id",
            referencedColumnName = "lot_id"
    )
    private BirdLotEntity birdLot;
}
