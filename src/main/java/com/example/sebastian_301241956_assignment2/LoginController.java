package com.example.sebastian_301241956_assignment2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private CustomerRepository customerRepository;
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
                        Customer customer = customerRepository.findByUsername(username);

            if (customer != null && customer.getPassword().equals(password)) {
                return "redirect:/home";
            } else {
                return "redirect:/login?error";
            }
    }
}
