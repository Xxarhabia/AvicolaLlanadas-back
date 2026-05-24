package com.msara.GestionAvicolaLlanadas.application.services;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.SaleRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.SaleResponse;

import java.util.List;

public interface SalesService {

    // Registrar venta completa (cabecera + detalles)
    GeneralResponse recordSale(SaleRequest request);

    // Listado de ventas
    List<SaleResponse> getSales();

    // Venta por ID con sus detalles
    GeneralResponse getSaleById(Long id);
}
