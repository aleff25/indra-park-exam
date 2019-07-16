package com.indraparkapi.domain.park;

import com.indraparkapi.domain.Model;

import java.util.Date;
import java.util.List;

public interface ParkService {

    /**
     * Search for vehicle in park
     * @param plate
     * @return founded {@link Model}
     */
    Vehicle vehicle(String plate);

    /**
     * Add a new vehicle in park
     * @param vehicle to adding
     */
    Vehicle add(Vehicle vehicle);

    /**
     * Update the existent vehicle in park
     * @param plate to search if the vehicle exists
     * @param vehicle to update
     */
    Vehicle update(String plate, Vehicle vehicle);

    /**
     * Remove existent vehicle in park
     * @param plate to search if the vehicle exists
     */
    void remove(String plate);

    /**
     * Search all vehicles in park
     * @return {@link List< Vehicle >}
     */
    List<Vehicle> list();

    /**
     * Search all vehicles in park by opeation
     * @param operation {@link Operation}
     * @param initialDate {@link Date} to search
     * @param finalDate {@link Date} to search
     * @return {@link List< Vehicle >}
     */
    List<Vehicle> list(String operation, Date initialDate, Date finalDate);
}
