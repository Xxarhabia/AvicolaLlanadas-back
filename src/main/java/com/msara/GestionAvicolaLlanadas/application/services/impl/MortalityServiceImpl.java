package com.msara.GestionAvicolaLlanadas.application.services.impl;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.MortalityRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.exceptions.BusinessException;
import com.msara.GestionAvicolaLlanadas.application.services.MortalityService;
import com.msara.GestionAvicolaLlanadas.domain.entities.BirdLotEntity;
import com.msara.GestionAvicolaLlanadas.domain.entities.MortalityEntity;
import com.msara.GestionAvicolaLlanadas.domain.repositories.BirdLotRepository;
import com.msara.GestionAvicolaLlanadas.domain.repositories.MortalityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MortalityServiceImpl implements MortalityService {

    private final MortalityRepository mortalityRepository;
    private final BirdLotRepository birdLotRepository;

    @Override
    public GeneralResponse recordMortality(MortalityRequest request) {
        BirdLotEntity lot = birdLotRepository.findById(request.birdLotId())
                .orElseThrow(() -> new EntityNotFoundException("Lote #" + request.birdLotId() + " no encontrado"));

        if (!lot.getStatus().equals("ACTIVO")) {
            throw new BusinessException("LOTE_INACTIVO",
                    "El lote #" + request.birdLotId() + "esta " + lot.getStatus()
                            + " y no permite registrar bajas");
        }

        if (request.numberCasualties() > lot.getCurrentQuantity()) {
            throw new BusinessException("BAJAS_EXCEDEN_STOCK",
                    "No se pueden registrar " + request.numberCasualties()
                            + " bajas. El lote solo tiene " + lot.getCurrentQuantity() + " aves");
        }

        MortalityEntity mortality = MortalityEntity.builder()
                .birdLot(lot)
                .numberCasualties(request.numberCasualties())
                .cause(request.cause())
                .notes(request.notes())
                .date(request.date() != null ? request.date() : LocalDate.now())
                .build();

        mortalityRepository.save(mortality);

        return new GeneralResponse("00", "Bajas registradas correctamente", true, mortality);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MortalityEntity> getMortalityByLot(Long lotId) {
        if (!birdLotRepository.existsById(lotId)) {
            throw new EntityNotFoundException("Lote #" + lotId + " no encontrado");
        }
        return mortalityRepository.findByBirdLotLotId(lotId);
    }
}
