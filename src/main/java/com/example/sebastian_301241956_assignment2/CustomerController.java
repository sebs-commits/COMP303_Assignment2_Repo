package com.example.sebastian_301241956_assignment2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @RequestMapping("/registerAccount")
    public String register() {
        return "register";
    }
    @RequestMapping("/register")
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("customerName") String customerName,
            @RequestParam("dob") LocalDate dob,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("postalCode") String postalCode,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone) {

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setCustomerName(customerName);
        customer.setDob(dob);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setPostalCode(postalCode);
        customer.setEmail(email);
        customer.setPhone(phone);

        customerRepository.save(customer);

        // Redirect to index page
        return "redirect:/index";
    }
}
