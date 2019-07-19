package com.indraparkapi.domain.calculator;

import com.indraparkapi.domain.Model;
import com.indraparkapi.domain.park.VehicleBuilder;
import com.indraparkapi.domain.park.Vehicle;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static com.indraparkapi.domain.Model.CAR;
import static com.indraparkapi.domain.Model.TRUCK;
import static com.indraparkapi.domain.park.Operation.ENTRANCE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorsTest {

    @Test
    public void calculatePayment() {

        long value = Calculators.INTEGRAL_TIME.calculate(getVehicle(CAR, DateUtil.yesterday()));

        assertThat(value).isEqualTo(360);
    }

    @Test
    public void calculatePayment_TRUCK() {

        long value = Calculators.INTEGRAL_TIME.calculate(getVehicle(TRUCK, DateUtil.yesterday()));

        assertThat(value).isEqualTo(840);
    }

    @Test
    public void calculatePayment_InvalidDate() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Calculators.INTEGRAL_TIME.calculate(getVehicle(TRUCK, DateUtil.tomorrow())))
            .withMessage("It's no possible to calculate time");
    }

    private Vehicle getVehicle(Model model, Date updated) {
        return new VehicleBuilder("NIX-1025").model(model)
            .operation(ENTRANCE)
            .createdAt(updated)
            .updatedAt(updated)
            .build();
    }
}