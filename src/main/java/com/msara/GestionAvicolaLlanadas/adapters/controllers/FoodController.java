package com.msara.GestionAvicolaLlanadas.adapters.controllers;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.FoodConsumptionRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RecordTypeFoodRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.FoodConsumptionResponse;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.FoodResponse;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.FoodService;
import com.msara.GestionAvicolaLlanadas.application.services.impl.FoodServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    // POST /api/v1/food
    // Registrar alimento nuevo o sumar stock si ya existe (T2 maneja el upsert)
    @PostMapping
    public ResponseEntity<GeneralResponse> recordFood(@Valid @RequestBody RecordTypeFoodRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(foodService.recordTypeFood(request));
    }

    // GET /api/v1/food
    @GetMapping
    public ResponseEntity<List<FoodResponse>> getAll() {
        return ResponseEntity.ok(foodService.reportFoodRecorded());
    }

    // POST /api/v1/food/consumption
    // Registrar consumo de alimento por lote (T1 descuenta stock en BD)
    @PostMapping("/consumption")
    public ResponseEntity<GeneralResponse> recordConsumption(
            @Valid @RequestBody FoodConsumptionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(foodService.recordFoodConsumption(request));
    }

    // GET /api/v1/food/consumption
    @GetMapping("/consumption")
    public ResponseEntity<List<FoodConsumptionResponse>> getAllConsumption() {
        return ResponseEntity.ok(foodService.reportFoodConsumption());
    }

    // GET /api/v1/food/consumption/lot/{lotId}
    @GetMapping("/consumption/lot/{lotId}")
    public ResponseEntity<List<FoodConsumptionResponse>> getConsumptionByLot(
            @PathVariable Long lotId) {
        return ResponseEntity.ok(foodService.reportFoodConsumptionByLot(lotId));
    }
}
