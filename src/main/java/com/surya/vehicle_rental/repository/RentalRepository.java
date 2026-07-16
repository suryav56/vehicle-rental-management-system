package com.surya.vehicle_rental.repository;

import com.surya.vehicle_rental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RentalRepository  extends JpaRepository<Rental, Long>{
}
