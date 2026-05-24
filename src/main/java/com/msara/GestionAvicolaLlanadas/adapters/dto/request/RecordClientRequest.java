package com.msara.GestionAvicolaLlanadas.adapters.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RecordClientRequest(
        @NotBlank(message = "El nombre completo es obligatorio")
        @Size(max = 80)
        String fullName,

        @NotBlank(message = "El documento es obligatorio")
        @Size(max = 10)
        String document,

        @Size(max = 10)
        String phone,

        String address
) {}
