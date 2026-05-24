package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RecordTypeFoodRequest(
        @NotBlank(message = "El tipo de alimento es obligatorio")
        @Size(max = 50)
        String typeFood,

        @NotNull @Positive
        BigDecimal availableQuantity,

        @NotBlank
        String unitMeasurement, // kg | g | lb | ton | L | mL | und

        @NotNull @PositiveOrZero
        BigDecimal unitPrice,

        @NotNull @PositiveOrZero
        BigDecimal minStock,

        Boolean isForSale,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dateInsert
) {}
