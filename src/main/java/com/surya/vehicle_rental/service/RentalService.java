package com.surya.vehicle_rental.service;

import com.surya.vehicle_rental.model.Rental;
import com.surya.vehicle_rental.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public void saveRental(Rental rental) {
        rentalRepository.save(rental);
    }

    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id).orElse(null);
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }
}
