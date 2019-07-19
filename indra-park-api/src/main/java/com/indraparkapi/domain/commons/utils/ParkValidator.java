package com.indraparkapi.domain.commons.utils;

import com.indraparkapi.domain.park.Operation;
import com.indraparkapi.domain.park.Vehicle;
import io.vavr.control.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParkValidator {

    private static final String PLATE_PATTERN = "[A-Z]{3}-[0-9]{4}";

    public static Validation<String, Boolean> validatePlate(String plate) {
        Pattern pattern = Pattern.compile(PLATE_PATTERN);
        Matcher matcher = pattern.matcher(plate);
        return matcher.matches() ? Validation.valid(true) :
            Validation.invalid("The plate informed its not valid");
    }

    public static Validation<String, Boolean> validate(Vehicle vehicle) {
        return Operation.of(vehicle.getOperation()).equals(Operation.ENTRANCE) ?
            Validation.valid(true) :
            Validation.invalid("Operation to calculate needs to be Entrance");
    }


}
