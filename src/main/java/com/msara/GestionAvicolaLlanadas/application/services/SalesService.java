package com.msara.GestionAvicolaLlanadas.application.services;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.SalesRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;

public interface SalesService {

    GeneralResponse recordSaleToCustomer(SalesRequest salesRequest);


}
