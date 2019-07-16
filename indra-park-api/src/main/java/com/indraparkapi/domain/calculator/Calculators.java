package com.indraparkapi.domain.calculator;

import com.indraparkapi.domain.Model;
import com.indraparkapi.domain.park.Operation;
import com.indraparkapi.domain.park.Vehicle;

import java.math.BigDecimal;

public enum Calculators implements CalculatorsVehicle {

    INTEGRAL_TIME {
        @Override
        public BigDecimal calculate(Vehicle vehicle, Model model) {

            if (Operation.of(vehicle.getOperation()).equals(Operation.ENTRANCE)) {

                vehicle.getModel();
            }

            return null;
        }
    }
}
