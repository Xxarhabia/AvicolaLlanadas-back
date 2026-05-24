package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private Long mortalityId;

    // FIX: FK apuntaba a "birt_lot_id" (typo)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bird_lot_id", referencedColumnName = "lot_id", nullable = false)
    private BirdLotEntity birdLot;

    // FIX: typo "numeber_caualties" corregido en nombre de columna
    @Column(name = "number_casualties", nullable = false)
    private Integer numberCasualties;

    @Column(length = 100)
    private String cause;

    // FIX: era Date, ahora LocalDate
    @Column(nullable = false)
    @Builder.Default
    private LocalDate date = LocalDate.now();

    // NUEVO: quién registró la baja
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registered_by", referencedColumnName = "user_id")
    private UserEntity registeredBy;

    @Column(columnDefinition = "TEXT")
    private String notes;
}
