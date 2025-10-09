package com.msara.GestionAvicolaLlanadas.application.services;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.ClosingLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RegisterBirdLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;

public interface BirdLotService {

    GeneralResponse registerBirdLot(RegisterBirdLotRequest birdLot);

    GeneralResponse closeBirdLot(Long id, ClosingLotRequest request);

}
