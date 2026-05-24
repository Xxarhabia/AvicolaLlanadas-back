package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CloseLotRequest(
        @NotNull(message = "La fecha de cierre es obligatoria")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate closeDate,

        // CERRADO | VENDIDO | PERDIDO
        @NotBlank
        String status
) {}
