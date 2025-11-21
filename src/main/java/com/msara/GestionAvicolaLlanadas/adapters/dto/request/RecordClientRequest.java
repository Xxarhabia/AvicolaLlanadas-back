package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import jakarta.validation.constraints.NotNull;

public record RecordClientRequest(@NotNull String fullName,
                                  String address,
                                  @NotNull String phone,
                                  @NotNull String document) {
}
