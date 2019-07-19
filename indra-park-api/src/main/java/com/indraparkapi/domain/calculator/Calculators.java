package com.indraparkapi.domain.calculator;

import com.indraparkapi.domain.Model;
import com.indraparkapi.domain.park.Vehicle;
import org.apache.commons.lang3.Validate;
import org.assertj.core.util.DateUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Class to map the types for calculate values, based on time the vehicle stays in park
 * Future, if needs to add another type to calculate the value, just put here the logic
 */
public enum Calculators implements CalculatorsVehicle {

    INTEGRAL_TIME {
        @Override
        public long calculate(Vehicle vehicle) {
            Validate.isTrue(vehicle.getUpdatedAt().before(DateUtil.now()), "It's no possible to calculate time");
            long hours = getDateDiff(vehicle.getUpdatedAt(), DateUtil.now(), TimeUnit.HOURS);
            return hours * Model.of(vehicle.getModel()).getValue();
        }
    };


    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInHours = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInHours,TimeUnit.MILLISECONDS);
    }
}
