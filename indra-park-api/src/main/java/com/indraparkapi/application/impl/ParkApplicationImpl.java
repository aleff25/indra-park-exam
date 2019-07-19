package com.indraparkapi.application.impl;

import com.indraparkapi.application.ParkApplication;
import com.indraparkapi.domain.calculator.Calculators;
import com.indraparkapi.domain.commons.exception.ErrorCode;
import com.indraparkapi.domain.commons.exception.ParkError;
import com.indraparkapi.domain.commons.exception.ParkException;
import com.indraparkapi.domain.commons.utils.ParkValidator;
import com.indraparkapi.domain.park.Operation;
import com.indraparkapi.domain.park.Vehicle;
import com.indraparkapi.domain.park.VehicleService;
import com.indraparkapi.domain.park.dashboard.Dashboard;
import org.assertj.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("parkApplication")
public class ParkApplicationImpl implements ParkApplication {

    private static final Logger LOG = LoggerFactory.getLogger(ParkApplicationImpl.class);

    @Autowired
    private VehicleService vehicleService;

    @Override
    public long calculate(String plate) {
        String msg;
        if (ParkValidator.validatePlate(plate).isInvalid()) {
            msg = String.format("Vehicle with plate %s not found", plate);
            callException(msg);

        }
        Vehicle vehicle = vehicleService.vehicle(plate);
        Operation operation = Operation.of(vehicle.getOperation());

        if(!operation.equals(Operation.ENTRANCE)) {
            msg = String.format("The vehicle %s is no longer in the park", vehicle);
            callException(msg);
        }

        long calculate = Calculators.INTEGRAL_TIME.calculate(vehicle);
        vehicle.setOperation(Operation.EXIT.getName());
        this.vehicleService.update(plate, vehicle);
        return calculate;
    }

    @Override
    public List<Vehicle> search(Operation operation, Date initialDate, Date finalDate) {
        if (operation.equals(Operation.NONE)) {
            return this.vehicleService.list(initialDate, finalDate);
        }
        return this.vehicleService.list(operation.getName(), initialDate, finalDate);
    }

    @Override
    public Vehicle store(Vehicle vehicle) {
        return this.vehicleService.add(vehicle);
    }

    @Override
    public Vehicle update(String plate, Vehicle vehicle) {
        return this.vehicleService.update(plate,vehicle);
    }

    @Override
    public void delete(String plate) {
        this.vehicleService.remove(plate);
    }

    @Override
    public List<Dashboard> dashboard(Date initialDate, Date finalDate) {
        List<Vehicle> list = this.vehicleService.list(initialDate, finalDate);

        Map<Date, Map<String, Long>> map = list.stream().map(vehicle -> {
            vehicle.setUpdatedAt(DateUtil.truncateTime(vehicle.getUpdatedAt()));
            return vehicle;
        }).collect(Collectors.groupingBy(Vehicle::getUpdatedAt,
            Collectors.groupingBy(Vehicle::getModel, Collectors.counting())));

        List<Dashboard> dashboards = new ArrayList<>();
        map.forEach( (dash, action) -> dashboards.add(new Dashboard(dash, action)));
        return dashboards;
    }

    private static void callException(String msg) {
        LOG.info(msg);
        ErrorCode error = ErrorCode.BAD_REQUEST;
        ParkError parkError = new ParkError.Builder(error.getCode(), msg).build();
        throw new ParkException(parkError, error.getHttpCode());
    }
}
