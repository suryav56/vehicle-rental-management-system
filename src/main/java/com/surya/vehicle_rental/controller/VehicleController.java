package com.surya.vehicle_rental.controller;

import com.surya.vehicle_rental.service.VehicleService;
import com.surya.vehicle_rental.model.Vehicle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());

        return "vehicles";
    }

    @GetMapping("/addVehicle")
    public String addVehicleForm(Model model) {
        model.addAttribute("vehicle",new Vehicle());

        return "addVehicle";
    }

    @PostMapping("/saveVehicle")
    public String saveVehicle(@ModelAttribute Vehicle vehicle) {

        vehicleService.saveVehicle(vehicle);

        return "redirect:/";
    }
}
