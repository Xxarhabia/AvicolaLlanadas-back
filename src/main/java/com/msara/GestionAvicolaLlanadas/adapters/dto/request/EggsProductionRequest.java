package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record EggsProductionRequest(
        @NotNull Long birdLotId,

        @NotNull @Positive
        Integer totalQuantity,

        @PositiveOrZero
        Integer cracked,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate date,

        String notes
) {
}
