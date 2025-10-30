package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FoodConsumptionRequest(@NotNull double quantityUsed,
                                     @NotNull String unit,
                                     @NotNull Long birdLotId,
                                     @NotNull String typeFood,
                                     @NotNull LocalDate dateInsert) {
}
