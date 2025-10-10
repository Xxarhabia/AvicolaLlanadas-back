package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecordTypeFoodRequest(@NotNull double availableQuantity,
                                    @NotBlank String name,
                                    @NotBlank String type,
                                    @NotBlank String unitMeasurement) {
}
