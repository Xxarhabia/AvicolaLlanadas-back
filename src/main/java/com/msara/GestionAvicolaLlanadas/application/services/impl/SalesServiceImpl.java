package com.msara.GestionAvicolaLlanadas.application.services.impl;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.SalesRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.SalesService;
import com.msara.GestionAvicolaLlanadas.domain.entities.SaleEntity;
import com.msara.GestionAvicolaLlanadas.domain.repositories.BirdLotRepository;
import com.msara.GestionAvicolaLlanadas.domain.repositories.FoodRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SalesServiceImpl implements SalesService {

    private FoodRepository foodRepository;
    private BirdLotRepository birdLotRepository;

    @Autowired
    public SalesServiceImpl(FoodRepository foodRepository, BirdLotRepository birdLotRepository) {
        this.foodRepository = foodRepository;
        this.birdLotRepository = birdLotRepository;
    }

    @Override
    public GeneralResponse recordSaleToCustomer(SalesRequest salesRequest) {

        SaleEntity saleEntity;
        if (salesRequest.typeSale().equalsIgnoreCase("aves")) {
            //restar la cantidad de aves y validar que haya la cantidad completa


            //registrar el detalle de la venta

            //registrar la venta

        } else if (salesRequest.typeSale().equalsIgnoreCase("alimento")) {
            //restar la cantidad de alimento y validar que haya la cantidad completa

            //registrar el detalle de la venta

            //registrar la venta

        } else {
            return new GeneralResponse("01", "Tipo de venta INCORRECTO!", false, null);
        }
        return null;
    }
}
