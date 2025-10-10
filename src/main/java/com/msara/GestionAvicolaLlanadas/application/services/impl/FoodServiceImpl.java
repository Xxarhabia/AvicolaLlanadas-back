package com.msara.GestionAvicolaLlanadas.application.services.impl;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.FoodConsumptionRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RecordTypeFoodRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.FoodService;
import com.msara.GestionAvicolaLlanadas.domain.entities.BirdLotEntity;
import com.msara.GestionAvicolaLlanadas.domain.entities.FoodConsumptionEntity;
import com.msara.GestionAvicolaLlanadas.domain.entities.FoodEntity;
import com.msara.GestionAvicolaLlanadas.domain.entities.UsedFoodEntity;
import com.msara.GestionAvicolaLlanadas.domain.repositories.BirdLotRepository;
import com.msara.GestionAvicolaLlanadas.domain.repositories.FoodConsumptionRepository;
import com.msara.GestionAvicolaLlanadas.domain.repositories.FoodRepository;
import com.msara.GestionAvicolaLlanadas.domain.repositories.UsedFoodRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
public class FoodServiceImpl implements FoodService {

    private FoodRepository foodRepository;
    private FoodConsumptionRepository foodConsumptionRepository;
    private BirdLotRepository birdLotRepository;
    private UsedFoodRepository usedFoodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository,BirdLotRepository lotRepository,
                           FoodConsumptionRepository foodConsumptionRepository, UsedFoodRepository usedFoodRepository) {
        this.foodRepository = foodRepository;
        this.foodConsumptionRepository = foodConsumptionRepository;
        this.birdLotRepository = lotRepository;
        this.usedFoodRepository = usedFoodRepository;
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

    @Override
    public GeneralResponse recordFoodConsumption(FoodConsumptionRequest request) {
        Date currentDate = new Date();

        BirdLotEntity birdLot = birdLotRepository.findById(request.birdLotId()).orElseThrow(
                () -> new RuntimeException("Bird Lot not found"));
        FoodEntity food = foodRepository.findById(request.foodId()).orElseThrow(
                () -> new RuntimeException("Food not found"));

        // guardamos la comida consumida por las aves
        FoodConsumptionEntity foodConsumption = FoodConsumptionEntity.builder()
                .date(currentDate)
                .quantityUsed(request.quantityUsed())
                .food(food)
                .birdLot(birdLot)
                .build();
        foodConsumptionRepository.save(foodConsumption);

        // Actualizamos la cantidad de alimento disponible
        double newAvailableQuantity = food.getAvailableQuantity() - request.quantityUsed();
        food.setAvailableQuantity(newAvailableQuantity);
        foodRepository.save(food);

        // guardamos en que fue usado la comida
        UsedFoodEntity usedFood = UsedFoodEntity.builder()
                .soldToClient(0)
                .usedInBird(1)
                .dateUse(currentDate)
                .food(food)
                .build();
        usedFoodRepository.save(usedFood);

        return new GeneralResponse("00", "The food consumed was successfully registered", true);
    }


}
