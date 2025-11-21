package com.msara.GestionAvicolaLlanadas.application.services;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RecordClientRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;

public interface ClientService {

    GeneralResponse recordClient(RecordClientRequest request);
    GeneralResponse getClients();
}
