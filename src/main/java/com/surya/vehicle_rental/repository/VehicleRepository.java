package com.surya.vehicle_rental.repository;

import com.surya.vehicle_rental.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long>{

}
