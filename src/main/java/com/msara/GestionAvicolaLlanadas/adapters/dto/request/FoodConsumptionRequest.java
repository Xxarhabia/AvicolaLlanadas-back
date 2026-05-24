package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FoodConsumptionRequest(
        @NotNull
        Long foodId,

        @NotNull
        Long birdLotId,

        @NotNull @Positive
        BigDecimal quantityUsed,

        @NotBlank
        String unit,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate date
) {}
