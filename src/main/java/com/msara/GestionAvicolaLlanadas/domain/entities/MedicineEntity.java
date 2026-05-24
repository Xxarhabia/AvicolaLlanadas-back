package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medicine")
@Builder
public class MedicineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private Long medicineId;

    @Column(nullable = false, unique = true, length = 80)
    private String name;

    @Column(nullable = false, length = 20)
    private String dose;

    @Column(name = "available_quantity", nullable = false)
    @Builder.Default
    private Integer availableQuantity = 0;

    // FIX: typo "unit_measuremen" corregido
    @Column(name = "unit_measurement", nullable = false, length = 10)
    private String unitMeasurement;

    // NUEVO: stock mínimo recomendado
    @Column(name = "min_stock", nullable = false)
    @Builder.Default
    private Integer minStock = 0;

    // NUEVO: fecha de vencimiento para trigger T6
    @Column(name = "expiry_date")
    private LocalDate expiryDate;
}
