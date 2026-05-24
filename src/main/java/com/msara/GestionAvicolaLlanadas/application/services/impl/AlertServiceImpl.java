package com.msara.GestionAvicolaLlanadas.application.services.impl;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.ResolveAlertRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.exceptions.BusinessException;
import com.msara.GestionAvicolaLlanadas.application.services.AlertService;
import com.msara.GestionAvicolaLlanadas.domain.entities.AlertLogEntity;
import com.msara.GestionAvicolaLlanadas.domain.entities.UserEntity;
import com.msara.GestionAvicolaLlanadas.domain.repositories.AlertLogRepository;
import com.msara.GestionAvicolaLlanadas.domain.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertLogRepository alertLogRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AlertLogEntity> getActiveAlerts() {
        return alertLogRepository.findByIsResolvedFalseOrderByCreatedAtDesc();
    }

    @Override
    public GeneralResponse resolveAlert(Long id, ResolveAlertRequest request) {
        AlertLogEntity alert = alertLogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Alerta #" + id + " no encontrada"));

        if (alert.getIsResolved()) {
            throw new BusinessException("ALERTA_YA_RESUELTA",
                    "La alerta #" + id + " ya fue marcada como resuelta");
        }

        UserEntity user = userRepository.findById(request.resolvedBy())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Usuario #" + request.resolvedBy() + " no encontrado"));

        alert.setIsResolved(true);
        alert.setResolvedAt(OffsetDateTime.now());
        alert.setUserEntity(user);
        alertLogRepository.save(alert);

        return new GeneralResponse("00", "Alerta resuelta correctamente", true, alert);
    }
}
