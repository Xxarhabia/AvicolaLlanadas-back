package com.msara.GestionAvicolaLlanadas.application.services.impl;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.CloseLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RegisterBirdLotRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.UpdateCurrentQuantityRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.BirdLotResponse;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.exceptions.BusinessException;
import com.msara.GestionAvicolaLlanadas.application.services.BirdLotService;
import com.msara.GestionAvicolaLlanadas.domain.entities.BirdLotAdjustmentEntity;
import com.msara.GestionAvicolaLlanadas.domain.entities.BirdLotEntity;
import com.msara.GestionAvicolaLlanadas.domain.repositories.BirdLotAdjustmentRepository;
import com.msara.GestionAvicolaLlanadas.domain.repositories.BirdLotRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BirdLotServiceImpl implements BirdLotService {

    private final BirdLotRepository birdLotRepository;
    private final BirdLotAdjustmentRepository birdLotAdjustmentRepository;

    @Override
    public GeneralResponse registerBirdLot(RegisterBirdLotRequest request) {
        BirdLotEntity lot = BirdLotEntity.builder()
                .birdType(request.birdType())
                .initialQuantity(request.initialQuantity())
                .currentQuantity(request.initialQuantity())
                .dateEntry(request.dateEntry() != null ? request.dateEntry() : LocalDate.now())
                .status("ACTIVO")
                .notes(request.notes())
                .build();

        birdLotRepository.save(lot);
        return new GeneralResponse("00", "Lote registrado exitosamente", true, toResponse(lot));
    }

    @Override
    public GeneralResponse closeBirdLot(Long id, CloseLotRequest request) {
        BirdLotEntity lot = findLotOrThrow(id);

        if (!lot.getStatus().equals("ACTIVO")) {
            throw new BusinessException("LOTE_YA_CERRADO", "El lote #" + id + " ya esta en estado: " + lot.getStatus());
        }

        String nuevoStatus = request.status().toUpperCase();
        if (!List.of("CERRADO", "VENDIDO", "PERDIDO").contains(nuevoStatus)) {
            throw new BusinessException("STATUS_INVALIDO", "Estado invalido. Use: CERRADO, VENDIDO, PERDIDO");
        }

        lot.setStatus(nuevoStatus);
        lot.setClosingDate(request.closeDate());
        birdLotRepository.save(lot);

        return new GeneralResponse("00", "Lote cerrado exitosamente", true, toResponse(lot));
    }

    @Override
    public GeneralResponse updateCurrentQuantity(Long id, UpdateCurrentQuantityRequest request) {
        BirdLotEntity lot = findLotOrThrow(id);

        if(!lot.getStatus().equals("ACTIVO")) {
            throw new BusinessException("LOTE_INACTIVO",
                    "Solo se puede modificar un lote en estado ACTIVO");
        }

        int delta = request.newQuantity() - lot.getCurrentQuantity();
        lot.setCurrentQuantity(request.newQuantity());
        birdLotRepository.save(lot);

        BirdLotAdjustmentEntity adj = BirdLotAdjustmentEntity.builder()
                .birdLot(lot)
                .quantityDelta(delta)
                .reason(request.reason().toUpperCase())
                .notes(request.notes())
                .build();
        birdLotAdjustmentRepository.save(adj);

        return new GeneralResponse("00", "Cantidad actualizada correctamente", true, toResponse(lot));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BirdLotResponse> reportBirdLots() {
        return birdLotRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BirdLotResponse> getBirdLotsByStatus(String status) {
        return birdLotRepository.findByStatus(status.toUpperCase())
                .stream().map(this::toResponse).toList();
    }

    // -- Helpers ------
    private BirdLotEntity findLotOrThrow(Long id) {
        return  birdLotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lote #" + id + " no encontrado"));
    }

    private BirdLotResponse toResponse(BirdLotEntity e) {
        return new BirdLotResponse(
                e.getLotId(), e.getBirdType(), e.getInitialQuantity(),
                e.getCurrentQuantity(), e.getDateEntry(), e.getClosingDate(),
                e.getStatus(), e.getNotes()
        );
    }
}
