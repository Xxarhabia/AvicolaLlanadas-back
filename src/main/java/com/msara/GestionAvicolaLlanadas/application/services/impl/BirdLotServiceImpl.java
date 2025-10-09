package com.msara.GestionAvicolaLlanadas.application.services.impl;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.StatusLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RegisterBirdLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.BirdLotService;
import com.msara.GestionAvicolaLlanadas.domain.entities.BirdLotEntity;
import com.msara.GestionAvicolaLlanadas.domain.repositories.BirdLotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BirdLotServiceImpl implements BirdLotService {

    private BirdLotRepository birdLotRepository;

    @Autowired
    public BirdLotServiceImpl(BirdLotRepository birdLotRepository) {
        this.birdLotRepository = birdLotRepository;
    }

    @Override
    public GeneralResponse registerBirdLot(RegisterBirdLotRequest registerBirdLotRequest) {
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
        return new GeneralResponse("00", "Bird register successful", true);
    }

    @Override
    public GeneralResponse closeBirdLot(Long id, StatusLotRequest request) {
        BirdLotEntity birdLot = birdLotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bird Lot not found"));

        birdLot.setStatus(request.status());
        birdLot.setClosingDate(new Date());
        birdLotRepository.save(birdLot);

        return new GeneralResponse("00", "Bird lot closed", true);
    }

    @Override
    public List<BirdLotEntity> reportBirdLots(StatusLotRequest request) {
        List<BirdLotEntity> birdLots;
        if (request.status() == 0 || request.status() == 1) {
            birdLots = birdLotRepository.findAllByStatus(request.status());
        } else {
            birdLots = birdLotRepository.findAll();
        }

        return birdLots;
    }


}
