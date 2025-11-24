package com.msara.GestionAvicolaLlanadas.adapters.controllers;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RecordClientRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/client")
public class ClientController {

    private ClientServiceImpl clientServiceImpl;

    @Autowired
    public ClientController(ClientServiceImpl clientServiceImpl) {
        this.clientServiceImpl = clientServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<GeneralResponse> recordClient(@RequestBody RecordClientRequest request) {
        return new ResponseEntity<>(clientServiceImpl.recordClient(request), HttpStatus.CREATED);
    }

    @GetMapping("/report")
    public ResponseEntity<GeneralResponse> getClientReport() {
        return new ResponseEntity<>(clientServiceImpl.getClients(), HttpStatus.OK);
    }
}
