package com.indraparkapi.application.impl;

import com.indraparkapi.application.ParkApplication;
import com.indraparkapi.domain.park.Operation;
import com.indraparkapi.domain.park.Vehicle;
import com.indraparkapi.domain.park.VehicleBuilder;
import com.indraparkapi.domain.park.VehicleService;
import com.indraparkapi.domain.park.dashboard.Dashboard;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.indraparkapi.domain.Model.CAR;
import static com.indraparkapi.domain.Model.TRUCK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkApplicationImplTest {

    @Autowired
    private ParkApplication parkApplication;

    @MockBean
    private VehicleService vehicleService;

    @Test
    public void dashboard() {

        when(vehicleService.list(any(), any())).thenReturn(list());

        List<Dashboard> dashboard = this.parkApplication.dashboard(DateUtil.yesterday(), DateUtil.tomorrow());

        assertThat(dashboard.isEmpty()).isFalse();
        assertThat(dashboard.iterator().next().getDate()).isEqualTo(null);
    }

    private List<Vehicle> list() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        Vehicle vehicle = new VehicleBuilder("NIX-1234").model(CAR).operation(Operation.ENTRANCE).createdAt(DateUtil.now()).updatedAt(DateUtil.now()).build();
        Vehicle vehicle2 = new VehicleBuilder("NIX-2345").model(CAR).operation(Operation.ENTRANCE).createdAt(DateUtil.now()).updatedAt(DateUtil.yesterday()).build();
        Vehicle vehicle3 = new VehicleBuilder("NIX-3456").model(CAR).operation(Operation.ENTRANCE).createdAt(DateUtil.now()).updatedAt(DateUtil.now()).build();
        Vehicle vehicle4 = new VehicleBuilder("NIX-4567").model(TRUCK).operation(Operation.ENTRANCE).createdAt(DateUtil.now()).updatedAt(DateUtil.yesterday()).build();
        Vehicle vehicle5 = new VehicleBuilder("NIX-5678").model(TRUCK).operation(Operation.ENTRANCE).createdAt(DateUtil.now()).updatedAt(DateUtil.now()).build();
        Vehicle vehicle6 = new VehicleBuilder("NIX-6789").model(TRUCK).operation(Operation.ENTRANCE).createdAt(DateUtil.now()).updatedAt(DateUtil.now()).build();

        vehicles.add(vehicle);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
        vehicles.add(vehicle4);
        vehicles.add(vehicle5);
        vehicles.add(vehicle6);
        return vehicles;
    }


}