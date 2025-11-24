package com.msara.GestionAvicolaLlanadas.adapters.controllers;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.SalesRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.SalesService;
import com.msara.GestionAvicolaLlanadas.application.services.impl.SalesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private SalesServiceImpl salesService;

    @Autowired
    public SalesController(SalesServiceImpl salesService) {
        this.salesService = salesService;
    }

    @PostMapping
    public ResponseEntity<GeneralResponse> recordSale(@RequestBody SalesRequest request) {
        return new ResponseEntity<>(salesService.recordSaleToCustomer(request), HttpStatus.OK);
    }
}
