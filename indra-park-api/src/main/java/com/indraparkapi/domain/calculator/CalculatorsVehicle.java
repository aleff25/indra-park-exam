package com.indraparkapi.domain.calculator;

import com.indraparkapi.domain.park.Vehicle;

public interface CalculatorsVehicle {

    /**
     * Calculates the value to pay based on time the vehicle stays in park
     *
     * @param vehicle to calculate based on type and value
     * @return
     */
    long calculate(Vehicle vehicle);
}
