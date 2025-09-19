package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "mortality")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MortalityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mortality_id")
    private long mortalityId;

    @Column(nullable = false)
    private Date date;

    @Column(name = "numeber_caualties", nullable = false)
    private int numberCasualties;

    @Column(nullable = false)
    private String cause;

    @ManyToOne
    @JoinColumn(
            name = "birt_lot_id",
            referencedColumnName = "lot_id"
    )
    private BirdLotEntity birdLot;
}
