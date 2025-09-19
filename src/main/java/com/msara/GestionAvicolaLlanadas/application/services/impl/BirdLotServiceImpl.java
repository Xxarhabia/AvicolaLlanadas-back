package com.msara.GestionAvicolaLlanadas.application.services.impl;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RegisterBirdLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.RegisterBirdLotReponse;
import com.msara.GestionAvicolaLlanadas.application.services.BirdLotService;
import com.msara.GestionAvicolaLlanadas.domain.entities.BirdLotEntity;
import com.msara.GestionAvicolaLlanadas.domain.repositories.BirdLotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
public class BirdLotServiceImpl implements BirdLotService {

    private BirdLotRepository birdLotRepository;

    @Autowired
    public BirdLotServiceImpl(BirdLotRepository birdLotRepository) {
        this.birdLotRepository = birdLotRepository;
    }

    @Override
    public RegisterBirdLotReponse registerBirdLot(RegisterBirdLotRequest registerBirdLotRequest) {
        BirdLotEntity birdLot = BirdLotEntity.builder()
                .dateEntry(new Date())
                .birdType(registerBirdLotRequest.birdType())
                .month(registerBirdLotRequest.month())
                .recordedAmount(registerBirdLotRequest.recordedAmount())
                .initialQuantity(registerBirdLotRequest.initialQuantity())
                .currentQuantity(registerBirdLotRequest.currentQuantity())
                .status(registerBirdLotRequest.status())
                .build();
        birdLotRepository.save(birdLot);
        return new RegisterBirdLotReponse("00", "Bird register successful", true);
    }
}
