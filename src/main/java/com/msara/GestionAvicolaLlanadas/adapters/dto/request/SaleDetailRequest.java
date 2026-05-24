package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record SaleDetailRequest(
        Long foodId,       // nulo si es venta de aves
        Long birdLotId,    // nulo si es venta de alimento

        @NotNull @Positive
        BigDecimal quantity,

        @NotNull @PositiveOrZero
        BigDecimal unitPrice
) {}
