package com.msara.GestionAvicolaLlanadas.adapters.controllers;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.FoodConsumptionRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RecordTypeFoodRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.impl.FoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/food")
public class FoodController {

    private FoodServiceImpl foodService;

    @Autowired
    public FoodController(FoodServiceImpl foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/record")
    public ResponseEntity<GeneralResponse> recordTypeFood(@RequestBody RecordTypeFoodRequest request) {
        return new ResponseEntity<>(foodService.recordTypeFood(request), HttpStatus.CREATED);
    }

    @PostMapping("/consumption")
    public ResponseEntity<GeneralResponse> recordFoodConsumption(@RequestBody FoodConsumptionRequest request) {
        return new ResponseEntity<>(foodService.recordFoodConsumption(request), HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<?> foodRecordedReport() {
        return new ResponseEntity<>(foodService.reportFoodRecorded(), HttpStatus.OK);
    }
}
