package com.msara.GestionAvicolaLlanadas.application.services.impl;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.EggsProductionRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.exceptions.BusinessException;
import com.msara.GestionAvicolaLlanadas.application.services.EggsProductionService;
import com.msara.GestionAvicolaLlanadas.domain.entities.BirdLotEntity;
import com.msara.GestionAvicolaLlanadas.domain.entities.EggsProductionEntity;
import com.msara.GestionAvicolaLlanadas.domain.repositories.BirdLotRepository;
import com.msara.GestionAvicolaLlanadas.domain.repositories.EggsProductionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EggsProductionServiceImpl implements EggsProductionService {

    private final EggsProductionRepository eggsProductionRepository;
    private final BirdLotRepository birdLotRepository;

    @Override
    public GeneralResponse recordEggsProduction(EggsProductionRequest request) {
        BirdLotEntity lot = birdLotRepository.findById(request.birdLotId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Lote #" + request.birdLotId() + " no encontrado"));

        if (!lot.getStatus().equals("ACTIVO")) {
            throw new BusinessException("LOTE_INACTIVO",
                    "El lote #" + request.birdLotId() + " no está activo");
        }

        int cracked = request.cracked() != null ? request.cracked() : 0;

        if (cracked > request.totalQuantity()) {
            throw new BusinessException("HUEVOS_INVALIDO",
                    "Los huevos rotos (" + cracked + ") no pueden superar el total ("
                            + request.totalQuantity() + ")");
        }

        EggsProductionEntity production = EggsProductionEntity.builder()
                .birdLot(lot)
                .totalQuantity(request.totalQuantity())
                .cracked(cracked)
                .date(request.date() != null ? request.date() : LocalDate.now())
                .notes(request.notes())
                .build();

        eggsProductionRepository.save(production);

        return new GeneralResponse("00", "Producción de huevos registrada correctamente",
                true, production);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EggsProductionEntity> getEggsByLot(Long lotId) {
        if (!birdLotRepository.existsById(lotId)) {
            throw new EntityNotFoundException("Lote #" + lotId + " no encontrado");
        }
        return eggsProductionRepository.findByBirdLotLotId(lotId);
    }
}
