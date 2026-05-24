package com.msara.GestionAvicolaLlanadas.adapters.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FoodResponse(
        Long foodId,
        String typeFood,
        BigDecimal availableQuantity,
        String unitMeasurement,
        BigDecimal unitPrice,
        BigDecimal minStock,
        Boolean isForSale,
        LocalDate dateInsert,
        boolean stockBajo  // true si availableQuantity <= minStock
) {}
