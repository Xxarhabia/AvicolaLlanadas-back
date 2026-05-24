package com.msara.GestionAvicolaLlanadas.application.services;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.EggsProductionRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.domain.entities.EggsProductionEntity;

import java.util.List;

public interface EggsProductionService {

    GeneralResponse recordEggsProduction(EggsProductionRequest request);

    List<EggsProductionEntity> getEggsByLot(Long lotId);
}
