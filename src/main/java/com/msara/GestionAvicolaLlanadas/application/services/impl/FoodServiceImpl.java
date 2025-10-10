package com.msara.GestionAvicolaLlanadas.application.services.impl;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RecordTypeFoodRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.FoodService;
import com.msara.GestionAvicolaLlanadas.domain.entities.FoodEntity;
import com.msara.GestionAvicolaLlanadas.domain.repositories.FoodRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FoodServiceImpl implements FoodService {

    private FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public GeneralResponse recordTypeFood(RecordTypeFoodRequest request) {
        FoodEntity food = FoodEntity.builder()
                .availableQuantity(request.availableQuantity())
                .name(request.name().toUpperCase())
                .type(request.type().toLowerCase())
                .unitMeasurement(request.unitMeasurement().toUpperCase())
                .build();
        foodRepository.save(food);
        return new GeneralResponse("00", "The food was successfully registered", true);
    }


}
