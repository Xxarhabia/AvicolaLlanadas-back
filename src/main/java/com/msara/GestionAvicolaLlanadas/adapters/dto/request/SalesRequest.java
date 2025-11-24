package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SalesRequest(@NotNull String typeSale,
                           @NotNull String typeProduct,
                           @Nullable String description,
                           @NotNull double quantity,
                           @NotNull double unitPrice,
                           @NotBlank String customerDoc,
                           @NotNull LocalDate dateSale) {
}
