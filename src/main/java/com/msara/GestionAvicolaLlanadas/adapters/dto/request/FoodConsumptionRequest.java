package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import jakarta.validation.constraints.NotNull;

public record FoodConsumptionRequest(@NotNull double quantityUsed, @NotNull Long birdLotId, @NotNull Long foodId) {
}
