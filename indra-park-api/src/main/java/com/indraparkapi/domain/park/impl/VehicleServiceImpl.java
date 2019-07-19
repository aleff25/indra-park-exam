package com.indraparkapi.domain.park.impl;

import com.indraparkapi.domain.commons.exception.ErrorCode;
import com.indraparkapi.domain.commons.exception.ParkError;
import com.indraparkapi.domain.commons.exception.ParkException;
import com.indraparkapi.domain.park.Operation;
import com.indraparkapi.domain.park.ParkRepository;
import com.indraparkapi.domain.park.Vehicle;
import com.indraparkapi.domain.park.VehicleService;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;


@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {

    private static final Logger LOG = LoggerFactory.getLogger(VehicleServiceImpl.class);

    @Autowired
    private ParkRepository parkRepository;

    @Override
    public Vehicle vehicle(String plate) {
        String validatedPlate = initValidation(plate);
        LOG.info("Start searching for vehicle with plate {}", plate);
        return this.parkRepository.findById(validatedPlate).orElseThrow(vehicleNotFound(plate));
    }

    private String initValidation(String plate) {
        Validate.notBlank(plate, "Please, inform the plate for found the vehicle");
        return plate.toUpperCase();
    }

    @Override
    public Vehicle add(Vehicle vehicle) {
        LOG.info("Adding new vehicle, with plate = {} ", vehicle.getPlate());
        String msg = String.format("Already found a vehicle with this plate, %s", vehicle.getPlate());
        this.parkRepository.findById(vehicle.getPlate()).ifPresent(v -> callException(msg));
        return this.parkRepository.save(vehicle);
    }

    @Override
    public Vehicle update(String plate, Vehicle vehicle) {
        LOG.info("Updating vehicle, with plate = {} ", plate);
        String msg = String.format("Already found a vehicle with this plate, %s", vehicle.getPlate());
        return Option.of(this.vehicle(vehicle.getPlate()))
            .peek(oldVehicle -> validateVehicle(oldVehicle, vehicle))
            .onEmpty(() -> callException(msg))
            .get();
    }

    private void validateVehicle(Vehicle oldVehicle, Vehicle newVehicle) {
        Validate.isTrue(oldVehicle.getPlate().equals(newVehicle.getPlate()),
            "Please, inform the correctly plate from existing vehicle in park");
        this.parkRepository.save(newVehicle);
    }

    @Override
    public void remove(String plate) {
        LOG.info("Removing vehicle, with plate = {} ", plate);

        String msg = String.format("Not found vehicle with plate %s to remove from park", plate);
        Option.of(this.vehicle(plate))
            .peek(v -> this.parkRepository.delete(v))
            .onEmpty(() -> callException(msg));
    }

    @Override
    public List<Vehicle> list() {
        LOG.info("Start search all vehicles in park");
        return Try.of(() -> this.parkRepository.findAll())
            .getOrElse(ArrayList::new);
    }

    @Override
    public List<Vehicle> list(String operation, Date initialDate, Date finalDate) {
        Validate.notBlank(operation, "Please, inform the plate for found the vehicle");
        LOG.info("Start searching for vehicle of model {}, and dates between [ {} and {} ]", operation, initialDate, finalDate);

        Operation oper = Operation.of(operation);
        return Try.of(() -> this.parkRepository
            .findByOperationAndUpdatedAtBetween(oper.getName(), initialDate, finalDate))
            .getOrElse(ArrayList::new);
    }

    @Override
    public List<Vehicle> list(Date initialDate, Date finalDate) {
        LOG.info("Start searching for vehicles dates between [ {} and {} ]", initialDate, finalDate);

        return Try.of(() -> this.parkRepository
            .findByUpdatedAtBetween(initialDate, finalDate))
            .getOrElse(ArrayList::new);
    }

    private static Supplier<ParkException> vehicleNotFound(String plate) {
        ErrorCode code = ErrorCode.NOT_FOUND;
        String msg = String.format("Vehicle not found with plate %s informed", plate);
        return () -> new ParkException(code.getCode(), msg, code.getHttpCode());
    }

    private static void callException(String msg) {
        LOG.info(msg);
        ErrorCode error = ErrorCode.BAD_REQUEST;
        ParkError parkError = new ParkError.Builder(error.getCode(), msg).build();
        throw new ParkException(parkError, error.getHttpCode());
    }

}
