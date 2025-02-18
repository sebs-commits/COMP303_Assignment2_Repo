package com.example.sebastian_301241956_assignment2.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
