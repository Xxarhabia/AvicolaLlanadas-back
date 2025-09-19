package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RegisterBirdLotRequest(@NotBlank String birdType,
                                     @NotBlank String month,
                                     @NotNull int recordedAmount,
                                     @NotNull int initialQuantity,
                                     @NotNull int currentQuantity,
                                     @NotNull int status) {
}
