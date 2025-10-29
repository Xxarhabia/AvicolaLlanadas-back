package com.msara.GestionAvicolaLlanadas.application.services;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.FoodConsumptionRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RecordTypeFoodRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.domain.entities.FoodEntity;

import java.util.List;

public interface FoodService {

    GeneralResponse recordTypeFood(RecordTypeFoodRequest request);

    GeneralResponse recordFoodConsumption(FoodConsumptionRequest request);

    List<FoodEntity> reportFoodRecorded();

}
 