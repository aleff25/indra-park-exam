package com.indraparkapi.domain.park;

import com.indraparkapi.domain.commons.exception.ErrorCode;
import com.indraparkapi.domain.commons.exception.ParkError;
import com.indraparkapi.domain.commons.exception.ParkException;

import java.util.stream.Stream;

/**
 * Operation status of a vehicule
 */
public enum Operation {
    ENTRANCE("ENTRANCE"),
    EXIT("EXIT");

    private final String name;

    Operation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Operation of(String name) {
        return Stream.of(values()).filter( operation -> operation.getName().equals(name)).findFirst()
            .orElseThrow(() -> new ParkException(ErrorCode.NOT_FOUND.getCode(), "Invalid Operation", 404));
    }
}
