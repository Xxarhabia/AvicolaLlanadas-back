package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bird_lot")
@Builder
public class BirdLotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private Long lotId;

    @Column(name = "bird_type", nullable = false, length = 50)
    private String birdType;

    @Column(name = "initial_quantity", nullable = false) // FIX: typo "quanitity" corregido
    private Integer initialQuantity;

    @Column(name = "current_quantity", nullable = false)
    private Integer currentQuantity;

    @Column(name = "date_entry", nullable = false)
    private LocalDate dateEntry;

    @Column(name = "closing_date")
    private LocalDate closingDate;

    // FIX: era int (0/1), ahora String con valores controlados por CHECK en BD
    // ACTIVO | CERRADO | VENDIDO | PERDIDO
    @Column(nullable = false, length = 20)
    @Builder.Default
    private String status = "ACTIVO";

    @Column(columnDefinition = "TEXT")
    private String notes;
}
