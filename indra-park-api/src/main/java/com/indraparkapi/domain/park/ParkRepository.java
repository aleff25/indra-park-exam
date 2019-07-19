package com.indraparkapi.domain.park;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ParkRepository extends JpaRepository<Vehicle, String> {

    List<Vehicle> findByOperationAndUpdatedAtBetween(String operation, Date initialDate, Date finalDate);

    List<Vehicle> findByUpdatedAtBetween(Date initialDate, Date finalDate);

}
