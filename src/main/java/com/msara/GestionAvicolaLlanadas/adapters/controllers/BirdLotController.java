package com.msara.GestionAvicolaLlanadas.adapters.controllers;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RegisterBirdLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.RegisterBirdLotReponse;
import com.msara.GestionAvicolaLlanadas.application.services.impl.BirdLotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bird-lot")
public class BirdLotController {

    private BirdLotServiceImpl birdLotService;

    @Autowired
    public BirdLotController(BirdLotServiceImpl birdLotService) {
        this.birdLotService = birdLotService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterBirdLotReponse> registerBirdLot(@RequestBody RegisterBirdLotRequest request) {
        return new ResponseEntity<>(birdLotService.registerBirdLot(request), HttpStatus.CREATED);
    }
}
