package com.indraparkapi.domain;

/**
 * Map the name and value of the models that can be implemented.
 * If you need to change the value to perform the calculation,
 * you must change the value property
 */
public enum Model {

    MOTORCYCLE("MOTORCYCLE", 10),
    CAR("CAR", 15),
    LIGHT_TRUCK("LIGHT TRUCK",20),
    TRUCK("TRUCK", 35);

    private final String name;
    private final int value;

    Model(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
