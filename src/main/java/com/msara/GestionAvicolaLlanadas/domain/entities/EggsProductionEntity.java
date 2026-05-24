package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "eggs_production", schema = "avicola_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EggsProductionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eggs_prod_id")
    private Long eggsProductionId;

    // FIX: FK apuntaba a "birt_lot_id" (typo)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bird_lot_id", referencedColumnName = "lot_id", nullable = false)
    private BirdLotEntity birdLot;

    @Column(name = "total_quantity", nullable = false)
    private Integer totalQuantity;

    // NUEVO: huevos rotos o defectuosos
    @Column(nullable = false)
    @Builder.Default
    private Integer cracked = 0;

    // FIX: era Date, ahora LocalDate
    @Column(nullable = false)
    @Builder.Default
    private LocalDate date = LocalDate.now();

    @Column(columnDefinition = "TEXT")
    private String notes;
}
