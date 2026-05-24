package com.msara.GestionAvicolaLlanadas.adapters.controllers;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.EggsProductionRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.EggsProductionService;
import com.msara.GestionAvicolaLlanadas.domain.entities.EggsProductionEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eggs-production")
@RequiredArgsConstructor
public class EggsProductionController {

    private final EggsProductionService eggsProductionService;

    // POST /api/v1/eggs-production
    @PostMapping
    public ResponseEntity<GeneralResponse> record(
            @Valid @RequestBody EggsProductionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eggsProductionService.recordEggsProduction(request));
    }

    // GET /api/v1/eggs-production/lot/{lotId}
    @GetMapping("/lot/{lotId}")
    public ResponseEntity<List<EggsProductionEntity>> getByLot(@PathVariable Long lotId) {
        return ResponseEntity.ok(eggsProductionService.getEggsByLot(lotId));
    }
}
