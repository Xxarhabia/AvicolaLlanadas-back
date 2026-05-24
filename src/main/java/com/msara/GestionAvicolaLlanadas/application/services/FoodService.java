package com.msara.GestionAvicolaLlanadas.application.services;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.FoodConsumptionRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RecordTypeFoodRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.FoodConsumptionResponse;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.FoodResponse;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.domain.entities.FoodConsumptionEntity;
import com.msara.GestionAvicolaLlanadas.domain.entities.FoodEntity;

import java.util.List;

public interface FoodService {

    // Registrar o sumar stock de un alimento (T2 maneja el upsert en BD)
    GeneralResponse recordTypeFood(RecordTypeFoodRequest request);

    // Registrar consumo (T1 descuenta stock en BD)
    GeneralResponse recordFoodConsumption(FoodConsumptionRequest request);

    // Inventario completo
    List<FoodResponse> reportFoodRecorded();

    // Historial de consumo
    List<FoodConsumptionResponse> reportFoodConsumption();

    // Consumo filtrado por loto
    List<FoodConsumptionResponse> reportFoodConsumptionByLot(Long id);
}
 