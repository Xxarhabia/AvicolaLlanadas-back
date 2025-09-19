package com.msara.GestionAvicolaLlanadas.application.services;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RegisterBirdLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.RegisterBirdLotReponse;
import com.msara.GestionAvicolaLlanadas.domain.entities.BirdLotEntity;

public interface BirdLotService {

    RegisterBirdLotReponse registerBirdLot(RegisterBirdLotRequest birdLot);

}
