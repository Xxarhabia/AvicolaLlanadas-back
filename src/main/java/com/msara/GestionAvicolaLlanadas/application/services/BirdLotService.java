package com.msara.GestionAvicolaLlanadas.application.services;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.CloseLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RegisterBirdLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.domain.entities.BirdLotEntity;

import java.util.List;

public interface BirdLotService {

    GeneralResponse registerBirdLot(RegisterBirdLotRequest birdLot);

    GeneralResponse closeBirdLot(Long id, CloseLotRequest request);

    List<BirdLotEntity> reportBirdLots();


}
