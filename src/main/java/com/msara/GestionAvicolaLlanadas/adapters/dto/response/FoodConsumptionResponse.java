package com.msara.GestionAvicolaLlanadas.adapters.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FoodConsumptionResponse(
        Long id,
        Long foodId,
        String typeFood,
        Long birdLotId,
        String birdType,
        BigDecimal quantityUsed,
        String unit,
        LocalDate date
) {}
