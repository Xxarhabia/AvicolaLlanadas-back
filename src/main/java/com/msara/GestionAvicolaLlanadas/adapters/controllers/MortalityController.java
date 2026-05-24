package com.msara.GestionAvicolaLlanadas.adapters.controllers;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.MortalityRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.MortalityService;
import com.msara.GestionAvicolaLlanadas.domain.entities.MortalityEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mortality")
@RequiredArgsConstructor
public class MortalityController {

    private final MortalityService mortalityService;

    // POST /api/v1/mortality
    // T3a actualiza bird_lot.current_quantity automáticamente
    @PostMapping
    public ResponseEntity<GeneralResponse> record(@Valid @RequestBody MortalityRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mortalityService.recordMortality(request));
    }

    // GET /api/v1/mortality/lot/{lotId}
    @GetMapping("/lot/{lotId}")
    public ResponseEntity<List<MortalityEntity>> getByLot(@PathVariable Long lotId) {
        return ResponseEntity.ok(mortalityService.getMortalityByLot(lotId));
    }
}
