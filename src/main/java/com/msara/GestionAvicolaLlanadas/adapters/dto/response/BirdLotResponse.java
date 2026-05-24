package com.msara.GestionAvicolaLlanadas.adapters.dto.response;

import java.time.LocalDate;

public record BirdLotResponse(
        Long lotId,
        String birdType,
        Integer initialQuantity,
        Integer currentQuantity,
        LocalDate dateEntry,
        LocalDate closingDate,
        String status,
        String notes
) {}
