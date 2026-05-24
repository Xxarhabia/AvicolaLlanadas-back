package com.msara.GestionAvicolaLlanadas.adapters.controllers;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RecordClientRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    // POST /api/v1/clients
    @PostMapping
    public ResponseEntity<GeneralResponse> create(@Valid @RequestBody RecordClientRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientService.recordClient(request));
    }

    // GET /api/v1/clients
    @GetMapping
    public ResponseEntity<GeneralResponse> getAll() {
        return ResponseEntity.ok(clientService.getClients());
    }

    // GET /api/v1/clients/{id}
    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }
}
