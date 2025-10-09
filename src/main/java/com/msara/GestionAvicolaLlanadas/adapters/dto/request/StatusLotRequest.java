package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StatusLotRequest(@NotNull @Size(min = 1, max = 1) int status) {
}
