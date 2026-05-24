package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Date;

public record RegisterBirdLotRequest(
        @NotBlank(message = "El tipo de ave es obligatorio")
        @Size(max = 50)
        String birdType,

        @NotNull @Positive(message = "La cantidad inicial debe ser mayor a 0")
        int initialQuantity,

        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotNull LocalDate dateEntry,

        String notes
) {}
