package com.msara.GestionAvicolaLlanadas.adapters.controllers;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.SaleRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.SaleResponse;
import com.msara.GestionAvicolaLlanadas.application.services.SalesService;
import com.msara.GestionAvicolaLlanadas.application.services.impl.SalesServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    // POST /api/v1/sales
    @PostMapping
    public ResponseEntity<GeneralResponse> recordSale(@Valid @RequestBody SaleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(salesService.recordSale(request));
    }

    // GET /api/v1/sales
    @GetMapping
    public ResponseEntity<List<SaleResponse>> getAll() {
        return ResponseEntity.ok(salesService.getSales());
    }

    // GET /api/v1/sales/{id}
    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(salesService.getSaleById(id));
    }
}
