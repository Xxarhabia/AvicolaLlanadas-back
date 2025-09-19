package com.msara.GestionAvicolaLlanadas.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @Column(nullable = false, name = "date_entry")
    private Date dateEntry;

    @Column(nullable = false, name = "bird_type", length = 50)
    @JsonProperty("bird_type")
    private String birdType;

    @Column(nullable = false)
    private String month;

    @Column(nullable = false, name = "recorded_amount")
    @JsonProperty("recorded_amount")
    private int recordedAmount;

    @Column(nullable = false, name = "initial_quanitity")
    @JsonProperty("initial_quantity")
    private int initialQuantity;

    @Column(nullable = false, name = "current_quantity")
    @JsonProperty("current_quantity")
    private int currentQuantity;

    private int status;
}
