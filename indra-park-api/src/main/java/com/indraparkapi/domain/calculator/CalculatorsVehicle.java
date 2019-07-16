package com.indraparkapi.domain.calculator;

import com.indraparkapi.domain.Model;
import com.indraparkapi.domain.park.Vehicle;

import java.math.BigDecimal;

public interface CalculatorsVehicle {

    /**
     * Calculates the value to pay based on time the vehicle stays in park
     *
     * @param vehicle
     * @return
     */
    BigDecimal calculate(Vehicle vehicle, Model model);
}
