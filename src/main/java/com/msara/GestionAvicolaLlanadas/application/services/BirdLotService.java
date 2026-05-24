package com.msara.GestionAvicolaLlanadas.application.services;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.CloseLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RegisterBirdLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.UpdateCurrentQuantityRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.BirdLotResponse;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.domain.entities.BirdLotEntity;

import java.util.List;

public interface BirdLotService {

    // Registgrar nuevo lote
    GeneralResponse registerBirdLot(RegisterBirdLotRequest request);

    // Cerrar lote manualmente
    GeneralResponse closeBirdLot(Long id, CloseLotRequest request);

    // Editar cantidad actual del lote (funcionalidad faltante)
    GeneralResponse updateCurrentQuantity(Long id, UpdateCurrentQuantityRequest request);

    // Listado de todos los lotes
    List<BirdLotResponse> reportBirdLots();

    // Lotes por estado (ACTIVO, CERRADO, VENDIDO)
    List<BirdLotResponse> getBirdLotsByStatus(String status);
}
