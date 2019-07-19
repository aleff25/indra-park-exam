package com.indraparkapi.domain.park;

import com.indraparkapi.domain.Model;

import java.util.Date;

public class VehicleBuilder {

    String plate;
    Model model;
    Operation operation;
    Date createdAt;
    Date updatedAt;

    public VehicleBuilder(String plate) {
        this.plate = plate;
    }

    public VehicleBuilder model(Model model) {
        this.model = model;
        return this;
    }

    public VehicleBuilder operation(Operation operation) {
        this.operation = operation;
        return this;
    }

    public VehicleBuilder createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public VehicleBuilder updatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Vehicle build() {
        return new Vehicle(this);
    }
}
