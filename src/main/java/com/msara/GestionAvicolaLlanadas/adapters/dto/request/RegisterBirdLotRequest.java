package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record RegisterBirdLotRequest(@NotBlank String birdType,
                                     @NotNull int initialQuantity,
                                     @NotNull int currentQuantity,
                                     @NotNull LocalDate startDate,
                                     @NotNull String status) {
}
