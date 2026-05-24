package com.msara.GestionAvicolaLlanadas.application.services;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.MortalityRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.domain.entities.MortalityEntity;

import java.util.List;

public interface MortalityService {

    // Registrar bajas en un lote (T3 actualiza current_quantity en BD)
    GeneralResponse recordMortality(MortalityRequest request);

    // Historial de mortalidad de un lote
    List<MortalityEntity> getMortalityByLot(Long lotId);
}
