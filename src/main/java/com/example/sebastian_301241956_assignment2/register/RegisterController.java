package com.example.sebastian_301241956_assignment2.register;

import com.example.sebastian_301241956_assignment2.customer.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class RegisterController {
    @Autowired
    private RegisterRepository registerRepository;

    @GetMapping("/registerAccount")
    public String register(Model model) {
        model.addAttribute("customer", new Customer());
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

        registerRepository.save(customer);

        // Redirect to index page
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("customerName") String customerName,
            @RequestParam("dob") LocalDate dob,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("postalCode") String postalCode,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            Model model) {

        boolean hasError = false;

        // Check each required field, I probably dont really need these, but whatevs
        if (username == null || username.trim().isEmpty()) {
            model.addAttribute("usernameError", "Username is required");
            hasError = true;
        }

        if (password == null || password.trim().isEmpty()) {
            model.addAttribute("passwordError", "Password is required");
            hasError = true;
        }

        if (dob == null) {
            model.addAttribute("dobError", "Date of Birth is required");
            hasError = true;
        }

        if (email == null || email.trim().isEmpty()) {
            model.addAttribute("emailError", "Email is required");
            hasError = true;
        }

        // If errors were found, return to the form
        if (hasError) {
            return "register";
        }

        try {
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

            registerRepository.save(customer);
            return "redirect:/";

        } catch (Exception e) {
            model.addAttribute("error", "Registration failed.try again.");
            return "register";
        }
    }
}
