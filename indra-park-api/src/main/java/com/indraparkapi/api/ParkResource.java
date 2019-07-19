package com.indraparkapi.api;

import com.indraparkapi.application.ParkApplication;
import com.indraparkapi.domain.park.Operation;
import com.indraparkapi.domain.park.Vehicle;
import com.indraparkapi.domain.park.dashboard.Dashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/parks")
public class ParkResource {

    @Autowired
    private ParkApplication parkApplication;

    @GetMapping
    public List<Vehicle> searchVehicles(@RequestParam(value = "operation") Operation operation,
                                        @RequestParam(value = "initialDate") Date initialDate,
                                        @RequestParam(value = "finalDate") Date finalDate) {
        return this.parkApplication.search(operation, initialDate, finalDate);
    }

    @GetMapping(value = "/{id}/calculate")
    public long calculate(@PathVariable("id") String plate) {
        return this.parkApplication.calculate(plate);
    }

    @GetMapping(value = "dashboard")
    public List<Dashboard> dashboard(@RequestParam(value = "initialDate") Date initialDate,
                                     @RequestParam(value = "finalDate") Date finalDate) {
        return this.parkApplication.dashboard(initialDate, finalDate);
    }

    @PostMapping
    public Vehicle store(@RequestBody Vehicle vehicle) {
        return this.parkApplication.store(vehicle);
    }

    @PutMapping
    public Vehicle update(@RequestParam("plate") String plate, @RequestBody Vehicle vehicle) {
        return this.parkApplication.update(plate, vehicle);
    }

    @DeleteMapping
    public void delete(@RequestParam("plate") String plate) {
        this.parkApplication.delete(plate);
    }
}
