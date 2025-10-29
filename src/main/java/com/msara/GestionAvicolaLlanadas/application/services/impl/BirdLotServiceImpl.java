package com.msara.GestionAvicolaLlanadas.application.services.impl;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.CloseLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RegisterBirdLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.BirdLotService;
import com.msara.GestionAvicolaLlanadas.domain.entities.BirdLotEntity;
import com.msara.GestionAvicolaLlanadas.domain.repositories.BirdLotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        int status = 0;
        if(registerBirdLotRequest.status().equalsIgnoreCase("active")){
            status = 1;
        }
        BirdLotEntity birdLot = BirdLotEntity.builder()
                .dateEntry(registerBirdLotRequest.startDate())
                .birdType(registerBirdLotRequest.birdType())
                .initialQuantity(registerBirdLotRequest.initialQuantity())
                .currentQuantity(registerBirdLotRequest.currentQuantity())
                .status(status)
                .build();
        birdLotRepository.save(birdLot);
        return new GeneralResponse("00", "Bird register successful", true, birdLot);
    }

    @Override
    public GeneralResponse closeBirdLot(Long id, CloseLotRequest request) {
        BirdLotEntity birdLot = birdLotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bird Lot not found"));
        int status = 1;
        if (request.status().equalsIgnoreCase("closed")) {
            status = 0;
        }

        birdLot.setStatus(status);
        birdLot.setClosingDate(request.closeDate());
        birdLotRepository.save(birdLot);
        return new GeneralResponse("00", "Bird lot closed", true, birdLot);
    }

    @Override
    public List<BirdLotEntity> reportBirdLots() {
        return birdLotRepository.findAll();
    }


}
