package com.surya.vehicle_rental.controller;

import com.surya.vehicle_rental.model.Customer;
import com.surya.vehicle_rental.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public String customers(Model model) {

        model.addAttribute("customers", customerService.getAllCustomers());

        return "customers";
    }

    @GetMapping("/addCustomer")
    public String addCustomer(Model model) {

        model.addAttribute("customer", new Customer());

        return "addCustomer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute Customer customer) {

        customerService.saveCustomer(customer);

        return "redirect:/customers";
    }

    @GetMapping("/editCustomer/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {

        model.addAttribute("customer",
                customerService.getCustomerById(id));

        return "addCustomer";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Long id) {

        customerService.deleteCustomer(id);

        return "redirect:/customers";
    }
}