package com.msara.GestionAvicolaLlanadas.application.services.impl;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.FoodConsumptionRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RecordTypeFoodRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.FoodConsumptionResponse;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.FoodResponse;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.exceptions.BusinessException;
import com.msara.GestionAvicolaLlanadas.application.services.FoodService;
import com.msara.GestionAvicolaLlanadas.domain.entities.BirdLotEntity;
import com.msara.GestionAvicolaLlanadas.domain.entities.FoodConsumptionEntity;
import com.msara.GestionAvicolaLlanadas.domain.entities.FoodEntity;
import com.msara.GestionAvicolaLlanadas.domain.repositories.BirdLotRepository;
import com.msara.GestionAvicolaLlanadas.domain.repositories.FoodConsumptionRepository;
import com.msara.GestionAvicolaLlanadas.domain.repositories.FoodRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final FoodConsumptionRepository foodConsumptionRepository;
    private final BirdLotRepository birdLotRepository;

    @Override
    public GeneralResponse recordTypeFood(RecordTypeFoodRequest request) {
        FoodEntity food = FoodEntity.builder()
                .typeFood(request.typeFood().toLowerCase().trim())
                .availableQuantity(request.availableQuantity())
                .unitMeasurement(request.unitMeasurement().toLowerCase())
                .unitPrice(request.unitPrice())
                .minStock(request.minStock())
                .isForSale(request.isForSale() != null && request.isForSale())
                .dateInsert(request.dateInsert() != null ? request.dateInsert() : LocalDate.now())
                .build();

        foodRepository.save(food);

        FoodEntity actual = foodRepository.findByTypeFoodIgnoreCase(request.typeFood()).orElse(food);
        return new GeneralResponse("00", "Alimento registrado correctamente", true, toResponse(actual));
    }

    @Override
    public GeneralResponse recordFoodConsumption(FoodConsumptionRequest request) {
        BirdLotEntity lot = birdLotRepository.findById(request.birdLotId())
                .orElseThrow(() -> new EntityNotFoundException("Lote #" + request.birdLotId() + " no encontrado"));

        if (!lot.getStatus().equals("ACTIVO")) {
            throw new BusinessException("LOTE_INACTIVO",
                    "El lote #" + request.birdLotId() + " está " + lot.getStatus() + " y no puede recibir alimento");
        }

        FoodEntity food = foodRepository.findById(request.foodId())
                .orElseThrow(() -> new EntityNotFoundException("Lote #" + request.foodId() + " no encontrado"));

        FoodConsumptionEntity consumption = FoodConsumptionEntity.builder()
                .food(food)
                .birdLot(lot)
                .quantityUsed(request.quantityUsed())
                .unit(request.unit())
                .date(request.date() != null ? request.date() : LocalDate.now())
                .build();

        foodConsumptionRepository.save(consumption);

        return new GeneralResponse("00", "Consumo registrado correctamente", true,
                toConsumptionResponse(consumption));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodResponse> reportFoodRecorded() {
        return foodRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodConsumptionResponse> reportFoodConsumption() {
        return foodConsumptionRepository.findAll().stream()
                .map(this::toConsumptionResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodConsumptionResponse> reportFoodConsumptionByLot(Long lotId) {
        return foodConsumptionRepository.findByBirdLotLotId(lotId).stream()
                .map(this::toConsumptionResponse).toList();
    }

    private FoodResponse toResponse(FoodEntity e) {
        return new FoodResponse(
                e.getFoodId(), e.getTypeFood(), e.getAvailableQuantity(),
                e.getUnitMeasurement(), e.getUnitPrice(), e.getMinStock(),
                e.getIsForSale(), e.getDateInsert(),
                e.getAvailableQuantity().compareTo(e.getMinStock()) <= 0
        );
    }

    private FoodConsumptionResponse toConsumptionResponse(FoodConsumptionEntity e) {
        return new FoodConsumptionResponse(
                e.getId(),
                e.getFood().getFoodId(), e.getFood().getTypeFood(),
                e.getBirdLot().getLotId(), e.getBirdLot().getBirdType(),
                e.getQuantityUsed(), e.getUnit(), e.getDate()
        );
    }
}
