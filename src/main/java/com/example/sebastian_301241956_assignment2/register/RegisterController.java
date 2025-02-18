package com.example.sebastian_301241956_assignment2.register;

import com.example.sebastian_301241956_assignment2.Customer;
import com.example.sebastian_301241956_assignment2.login.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class RegisterController {
    @Autowired
    private RegisterRepository registerRepository;

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

        registerRepository.save(customer);

        // Redirect to index page
        return "redirect:/";
    }
}
