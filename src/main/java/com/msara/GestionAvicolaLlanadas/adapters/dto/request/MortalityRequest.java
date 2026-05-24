package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record MortalityRequest(
        @NotNull Long birdLotId,

        @NotNull @Positive
        Integer numberCasualties,

        @Size(max = 100)
        String cause,

        String notes,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate date
) {}
