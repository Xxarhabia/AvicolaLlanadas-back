package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "medication_application")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicationApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "med_app_id")
    private Long medAppId; // FIX: era "MedAppId" (mayúscula incorrecta)

    // FIX: FK apuntaba a "birt_lot_id" (typo), corregido a "bird_lot_id"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bird_lot_id", referencedColumnName = "lot_id", nullable = false)
    private BirdLotEntity birdLot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id", referencedColumnName = "medicine_id", nullable = false)
    private MedicineEntity medicine;

    @Column(name = "quantity_used", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantityUsed;

    // FIX: era Date, ahora OffsetDateTime para alinearse con TIMESTAMPTZ de la BD
    @Column(nullable = false)
    @Builder.Default
    private OffsetDateTime date = OffsetDateTime.now();

    // NUEVO: quién aplicó el medicamento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registered_by", referencedColumnName = "user_id")
    private UserEntity registeredBy;

    @Column(columnDefinition = "TEXT")
    private String notes;
}
