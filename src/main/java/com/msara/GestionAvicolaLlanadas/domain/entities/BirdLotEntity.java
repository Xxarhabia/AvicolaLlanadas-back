package com.msara.GestionAvicolaLlanadas.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column(nullable = false, name = "date_entry")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEntry;

    @Column(nullable = true, name = "closing_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate closingDate;

    @Column(nullable = false, name = "bird_type", length = 50)
    @JsonProperty("bird_type")
    private String birdType;

    @Column(nullable = false, name = "initial_quanitity")
    @JsonProperty("initial_quantity")
    private int initialQuantity;

    @Column(nullable = false, name = "current_quantity")
    @JsonProperty("current_quantity")
    private int currentQuantity;

    private int status;
}
