package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RecordTypeFoodRequest(@NotNull double availableQuantity,
                                    @NotBlank String foodType,
                                    @NotBlank String unit,
                                    @NotBlank LocalDate dateInsert) {
}
