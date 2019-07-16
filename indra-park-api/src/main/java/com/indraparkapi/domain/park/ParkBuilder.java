package com.indraparkapi.domain.park;

import com.indraparkapi.domain.Model;

import java.util.Date;

public class ParkBuilder {

    String plate;
    Model model;
    Operation operation;
    Date createdAt;
    Date updatedAt;

    public ParkBuilder(String plate) {
        this.plate = plate;
    }

    public ParkBuilder model(Model model) {
        this.model = model;
        return this;
    }

    public ParkBuilder operation(Operation operation) {
        this.operation = operation;
        return this;
    }

    public ParkBuilder createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ParkBuilder updatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Vehicle build() {
        return new Vehicle(this);
    }
}
