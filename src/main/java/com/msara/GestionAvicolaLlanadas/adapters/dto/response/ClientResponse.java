package com.msara.GestionAvicolaLlanadas.adapters.dto.response;

import java.time.OffsetDateTime;

public record ClientResponse(
        Long clientId,
        String fullName,
        String document,
        String phone,
        String address,
        Boolean isActive,
        OffsetDateTime createdAt
) {}
