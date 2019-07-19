package com.indraparkapi.application;

import com.indraparkapi.domain.park.Operation;
import com.indraparkapi.domain.park.Vehicle;
import com.indraparkapi.domain.park.dashboard.Dashboard;

import java.util.Date;
import java.util.List;

public interface ParkApplication {

    /**
     * Search for the vehicle and calculate the amount to pay for the time stayed in the park
     * @param plate to search
     * @return the value to pay
     */
    long calculate(String plate);

    /**
     * Search all vehicles with operation or none
     * @param operation
     * @param initialDate initial date
     * @param finalDate final date
     * @return {@link List<Vehicle>} vehicles
     */
    List<Vehicle> search(Operation operation, Date initialDate, Date finalDate);

    List<Dashboard> dashboard(Date initialDate, Date finalDate);

    /**
     * Store new vehicle in park
     * @param vehicle to store
     * @return vehicle stored
     */
    Vehicle store(Vehicle vehicle);

    /**
     * Update the vehicle in park
     * @param plate to search the vehicle
     * @param vehicle with new informations
     * @return updated vehicle
     */
    Vehicle update(String plate, Vehicle vehicle);

    /**
     * Delete the vehicle
     * @param plate
     */
    void delete(String plate);
}
