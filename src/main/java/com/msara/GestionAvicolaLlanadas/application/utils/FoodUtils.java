package com.msara.GestionAvicolaLlanadas.application.utils;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RecordTypeFoodRequest;

public class FoodUtils {

    public double convertUnitToKg(RecordTypeFoodRequest request) {
        double foodQuantityConvert;
        if (request.unit().equalsIgnoreCase("KG")) {
            return request.availableQuantity();
        } else if (request.unit().equalsIgnoreCase("LBS")) {
            foodQuantityConvert = request.availableQuantity() * 0.453592;
            return Math.round(foodQuantityConvert * 100) / 100.0;
        } else if (request.unit().equalsIgnoreCase("G")) {
            foodQuantityConvert = request.availableQuantity() * 0.001;
            return Math.round(foodQuantityConvert * 100) / 100.0;
        }
        return -1;
    }
}
