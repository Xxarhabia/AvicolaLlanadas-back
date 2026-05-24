package com.msara.GestionAvicolaLlanadas.adapters.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record SaleResponse(
        Long saleId,
        Long clientId,
        String clientName,
        OffsetDateTime date,
        String saleType,
        BigDecimal totalAmount,
        String paymentMethod,
        String status,
        List<SaleDetailResponse> details
) {
}
