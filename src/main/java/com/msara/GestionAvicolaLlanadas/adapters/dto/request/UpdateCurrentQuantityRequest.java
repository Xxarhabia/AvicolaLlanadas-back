package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateCurrentQuantityRequest(
        @NotNull @Positive
        Integer newQuantity,

        @NotBlank
        String reason, // INGRESO | TRASLADO | AJUSTE

        String notes
) {}
