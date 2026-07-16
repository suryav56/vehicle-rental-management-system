package com.surya.vehicle_rental.controller;

import com.surya.vehicle_rental.model.Rental;
import com.surya.vehicle_rental.model.Vehicle;
import com.surya.vehicle_rental.model.Customer;
import com.surya.vehicle_rental.service.CustomerService;
import com.surya.vehicle_rental.service.RentalService;
import com.surya.vehicle_rental.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RentalController {

    private final RentalService rentalService;
    private final VehicleService vehicleService;
    private final CustomerService customerService;

    public RentalController(RentalService rentalService,
                            VehicleService vehicleService,
                            CustomerService customerService) {

        this.rentalService = rentalService;
        this.vehicleService = vehicleService;
        this.customerService = customerService;
    }

    @GetMapping("/rentals")
    public String rentals(Model model) {

        model.addAttribute("rentals", rentalService.getAllRentals());

        return "rentals";
    }

    @GetMapping("/addRental")
    public String addRental(Model model) {

        model.addAttribute("rental", new Rental());

        model.addAttribute("vehicles",
                vehicleService.getAvailableVehicles());

        model.addAttribute("customers",
                customerService.getAllCustomers());

        return "addRental";
    }

    @PostMapping("/saveRental")
    public String saveRental(@RequestParam Long vehicleId,
                             @RequestParam Long customerId,
                             @ModelAttribute Rental rental) {

        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        Customer customer = customerService.getCustomerById(customerId);

        if (vehicle == null || customer == null) {
            return "redirect:/addRental";
        }

        // Don't allow renting an unavailable vehicle
        if (!vehicle.isAvailable()) {
            return "redirect:/addRental";
        }

        rental.setVehicle(vehicle);
        rental.setCustomer(customer);
        rental.setStatus("RENTED");

        vehicle.setAvailable(false);

        vehicleService.saveVehicle(vehicle);
        rentalService.saveRental(rental);

        return "redirect:/rentals";
    }

}