package com.msara.GestionAvicolaLlanadas.application.utils;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RecordTypeFoodRequest;

public class FoodUtils {

    public double convertUnitToKg(double availableQuantity, String unit) {
        double foodQuantityConvert;
        if (unit.equalsIgnoreCase("KG")) {
            return availableQuantity;
        } else if (unit.equalsIgnoreCase("LBS")) {
            foodQuantityConvert = availableQuantity * 0.453592;
            return Math.round(foodQuantityConvert * 100) / 100.0;
        } else if (unit.equalsIgnoreCase("G")) {
            foodQuantityConvert = availableQuantity * 0.001;
            return Math.round(foodQuantityConvert * 100) / 100.0;
        }
        return -1;
    }
}
