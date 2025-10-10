package com.msara.GestionAvicolaLlanadas.application.services;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RecordTypeFoodRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;

public interface FoodService {

    GeneralResponse recordTypeFood(RecordTypeFoodRequest request);

}
 