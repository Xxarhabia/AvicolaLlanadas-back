package com.msara.GestionAvicolaLlanadas.adapters.controllers;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.ResolveAlertRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.AlertService;
import com.msara.GestionAvicolaLlanadas.domain.entities.AlertLogEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    // GET /api/v1/alerts
    // Retorna todas las alertas activas (is_resolved = false) para el panel del frontend
    @GetMapping
    public ResponseEntity<List<AlertLogEntity>> getActive() {
        return ResponseEntity.ok(alertService.getActiveAlerts());
    }

    // PATCH /api/v1/alerts/{id}/resolve
    @PatchMapping("/{id}/resolve")
    public ResponseEntity<GeneralResponse> resolve(
            @PathVariable Long id,
            @Valid @RequestBody ResolveAlertRequest request) {
        return ResponseEntity.ok(alertService.resolveAlert(id, request));
    }
}
