package com.msara.GestionAvicolaLlanadas.adapters.controllers;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.StatusLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RegisterBirdLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.impl.BirdLotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bird-lot")
public class BirdLotController {

    private BirdLotServiceImpl birdLotService;

    @Autowired
    public BirdLotController(BirdLotServiceImpl birdLotService) {
        this.birdLotService = birdLotService;
    }

    @PostMapping("/register")
    public ResponseEntity<GeneralResponse> registerBirdLot(@RequestBody RegisterBirdLotRequest request) {
        return new ResponseEntity<>(birdLotService.registerBirdLot(request), HttpStatus.CREATED);
    }

    @PutMapping("/closing/{id}")
    public ResponseEntity<GeneralResponse> closeBirdLot(@PathVariable Long id, @RequestBody StatusLotRequest request) {
        return new ResponseEntity<>(birdLotService.closeBirdLot(id, request), HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<?> birtBatchReport(@RequestBody StatusLotRequest request) {
        return new ResponseEntity<>(birdLotService.reportBirdLots(request), HttpStatus.OK);
    }
}
