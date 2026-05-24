package com.msara.GestionAvicolaLlanadas.adapters.controllers;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.CloseLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RegisterBirdLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.UpdateCurrentQuantityRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.BirdLotResponse;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.BirdLotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/bird-lots")
@RequiredArgsConstructor
public class BirdLotController {

    private BirdLotService birdLotService;

    // POST /api/v1/bird-lots
    @PostMapping
    public ResponseEntity<GeneralResponse> register(@Valid @RequestBody RegisterBirdLotRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(birdLotService.registerBirdLot(request));
    }

    // GET /api/v1/bird-lots
    @GetMapping
    public ResponseEntity<List<BirdLotResponse>> getAll() {
        return ResponseEntity.ok(birdLotService.reportBirdLots());
    }

    // GET /api/v1/bird-lots?status=ACTIVO
    @GetMapping("/by-status")
    public ResponseEntity<List<BirdLotResponse>> getByStatus(@RequestParam String status) {
        return ResponseEntity.ok(birdLotService.getBirdLotsByStatus(status));
    }

    // PATCH /api/v1/bird-lots/{id}/close
    @PatchMapping("/{id}/close")
    public ResponseEntity<GeneralResponse> close(@PathVariable Long id, @Valid @RequestBody CloseLotRequest request) {
        return ResponseEntity.ok(birdLotService.closeBirdLot(id, request));
    }

    // PATCH /api/v1/bird-lots/{id}/quantity
    // Funcionalidad faltante: editar cantidad actual
    @PatchMapping("/{id}/quantity")
    public ResponseEntity<GeneralResponse> updateQuantity(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCurrentQuantityRequest request) {
        return ResponseEntity.ok(birdLotService.updateCurrentQuantity(id, request));
    }
}
