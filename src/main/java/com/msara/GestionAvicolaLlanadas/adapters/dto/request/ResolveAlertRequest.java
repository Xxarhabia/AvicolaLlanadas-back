package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import jakarta.validation.constraints.NotNull;

public record ResolveAlertRequest(
        @NotNull Long resolvedBy
) {}
