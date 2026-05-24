package com.msara.GestionAvicolaLlanadas.adapters.dto.response;

import java.math.BigDecimal;

public record SaleDetailResponse(
        Long detailId,
        Long foodId,
        String typeFood,
        Long birdLotId,
        String birdType,
        BigDecimal quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal
) {}
