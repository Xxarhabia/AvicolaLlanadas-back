package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long MedAppId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false, name = "quantity_used")
    private Double quantityUsed;

    @ManyToOne
    @JoinColumn(
            name = "birt_lot_id",
            referencedColumnName = "lot_id"
    )
    private BirdLotEntity birdLot;

    @ManyToOne
    @JoinColumn(
            name = "medicine_id",
            referencedColumnName = "medicine_id"
    )
    private MedicineEntity medicine;
}
