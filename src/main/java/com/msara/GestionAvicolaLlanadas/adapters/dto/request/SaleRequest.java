package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record SaleRequest(
        @NotNull(message = "El cliente es obligatorio")
        Long clientId,

        @NotBlank(message = "El tipo de venta es obligatorio")
        String saleType, // AVES | ALIMENTO

        @NotBlank
        String paymentMethod, // EFECTIVO | TRANSFERENCIA | CREDITO

        @NotEmpty(message = "La venta debe tener al menos un producto")
        List<SaleDetailRequest> details
) {
}
