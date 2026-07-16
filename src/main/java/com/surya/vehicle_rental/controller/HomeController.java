package com.surya.vehicle_rental.controller;

import com.surya.vehicle_rental.service.CustomerService;
import com.surya.vehicle_rental.service.RentalService;
import com.surya.vehicle_rental.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final VehicleService vehicleService;
    private final CustomerService customerService;
    private final RentalService rentalService;

    public HomeController(VehicleService vehicleService,
                          CustomerService customerService,
                          RentalService rentalService) {

        this.vehicleService = vehicleService;
        this.customerService = customerService;
        this.rentalService = rentalService;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("vehicleCount",
                vehicleService.getVehicleCount());

        model.addAttribute("availableCount",
                vehicleService.getAvailableVehicleCount());

        model.addAttribute("customerCount",
                customerService.getCustomerCount());

        model.addAttribute("rentalCount",
                rentalService.getRentalCount());

        return "index";
    }

}