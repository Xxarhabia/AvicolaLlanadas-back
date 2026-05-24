package com.msara.GestionAvicolaLlanadas.application.services;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.ResolveAlertRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.domain.entities.AlertLogEntity;

import java.util.List;

public interface AlertService {

    // Alertas activas (no resueltas) para el panel del frontend
    List<AlertLogEntity> getActiveAlerts();

    // Marcar alerta como resuelta
    GeneralResponse resolveAlert(Long id, ResolveAlertRequest request);
}
