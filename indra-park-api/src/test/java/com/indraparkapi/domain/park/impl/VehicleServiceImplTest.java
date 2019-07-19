package com.indraparkapi.domain.park.impl;

import com.indraparkapi.domain.Model;
import com.indraparkapi.domain.commons.exception.ParkException;
import com.indraparkapi.domain.park.VehicleBuilder;
import com.indraparkapi.domain.park.ParkRepository;
import com.indraparkapi.domain.park.Vehicle;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.indraparkapi.domain.Model.TRUCK;
import static com.indraparkapi.domain.park.Operation.ENTRANCE;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleServiceImplTest {

    @Autowired
    private VehicleServiceImpl parkService;

    @MockBean
    private ParkRepository parkRepository;

    @Test
    public void vehicle() {

        when(parkRepository.findById(anyString())).thenReturn(Optional.of(getVehicle()));

        Vehicle vehicle = parkService.vehicle("NIX-1025");

        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getModel()).isEqualTo(Model.CAR.getName());
    }

    @Test
    public void vehicle_NoutFound() {
        assertThatExceptionOfType(ParkException.class)
            .isThrownBy(() -> parkService.vehicle("NIX-1094"))
            .withMessageContaining(" Vehicle not found with plate NIX-1094 informed");
    }

    @Test
    public void add() {
        when(parkRepository.save(any())).thenReturn(getVehicle());

        parkService.add(getVehicle());
        verify(parkRepository, times(1)).save(any());
    }

    @Test
    public void add_NotPermited() {
        when(parkRepository.findById(anyString())).thenReturn(Optional.of(getVehicle()));

        assertThatExceptionOfType(ParkException.class)
            .isThrownBy(() -> parkService.add(getVehicle()))
            .withMessageContaining("Already found a vehicle with this plate, NIX-1025");

        verify(parkRepository, never()).save(any());
    }

    @Test
    public void update() {
        when(parkRepository.findById("NIX-1025")).thenReturn(Optional.of(getVehicle()));

        Vehicle vehicle = getVehicle();
        vehicle.setModel(TRUCK.getName());
        parkService.update("NIX-1025", vehicle);

        ArgumentCaptor<Vehicle> captor = ArgumentCaptor.forClass(Vehicle.class);

        verify(parkRepository, times(1)).save(captor.capture());

        Vehicle newVehicle = captor.getValue();
        assertThat(newVehicle.getModel()).isEqualTo(TRUCK.getName());

    }

    @Test
    public void remove() {
        String plate = "NIX-1025";
        when(parkRepository.findById(plate)).thenReturn(Optional.of(getVehicle()));

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        doNothing().when(parkRepository).deleteById(captor.capture());
        parkService.remove(plate);

        assertThat(plate).isEqualTo(captor.getValue());
    }

    @Test
    public void list() {
        when(parkRepository.findAll()).thenReturn(Collections.singletonList(getVehicle()));

        List<Vehicle> list = parkService.list();

        assertThat(list.isEmpty()).isFalse();
        verify(parkRepository, times(1)).findAll();
    }

    @Test
    public void list_NoneFound() {
        when(parkRepository.findAll()).thenReturn(Collections.emptyList());

        List<Vehicle> list = parkService.list();

        assertThat(list.isEmpty()).isTrue();
        verify(parkRepository, times(1)).findAll();
    }

    @Test
    public void listWithParams() {
        when(parkRepository.findByOperationAndUpdatedAtBetween(anyString(), any(), any()))
            .thenReturn(Collections.singletonList(getVehicle()));

        List<Vehicle> list = parkService.list(ENTRANCE.getName(), DateUtil.now(), DateUtil.now());

        assertThat(list.isEmpty()).isFalse();

        Vehicle vehicle = list.iterator().next();
        assertThat(DateUtil.truncateTime(vehicle.getCreatedAt())).isEqualTo(DateUtil.truncateTime(DateUtil.now()));
        assertThat(vehicle.getOperation()).isEqualTo(ENTRANCE.getName());

        verify(parkRepository, times(1))
            .findByOperationAndUpdatedAtBetween(anyString(), any(), any());
    }

    @Test
    public void listWithParams_OnlyDates() {
        when(parkRepository.findByUpdatedAtBetween(any(), any()))
            .thenReturn(Collections.singletonList(getVehicle()));

        List<Vehicle> list = parkService.list(DateUtil.now(), DateUtil.now());

        assertThat(list.isEmpty()).isFalse();

        Vehicle vehicle = list.iterator().next();
        assertThat(DateUtil.truncateTime(vehicle.getCreatedAt())).isEqualTo(DateUtil.truncateTime(DateUtil.now()));
        assertThat(vehicle.getOperation()).isEqualTo(ENTRANCE.getName());

        verify(parkRepository, times(1))
            .findByUpdatedAtBetween(any(), any());
    }

    private Vehicle getVehicle() {
        return new VehicleBuilder("NIX-1025").model(Model.CAR)
            .operation(ENTRANCE)
            .createdAt(DateUtil.now())
            .build();
    }
}

