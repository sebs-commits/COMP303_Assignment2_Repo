package com.example.sebastian_301241956_assignment2.customer;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {
    @Autowired
    private  CustomerRepository customerRepository;
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/editInformation")
    public String edit(Model model, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        Customer customer = customerRepository.findById(customerId).orElse(null);
        model.addAttribute("customer", customer);
        return "edit-customer";
    }

    @PostMapping("/edit")
    public String updateAccount(
            @RequestParam("address") String address,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            HttpSession session
    ) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        Customer customer = customerRepository.findById(customerId).orElse(null);
        customer.setAddress(address);
        customer.setPhone(phone);
        customer.setEmail(email);
        customerRepository.save(customer);
        return "redirect:/home";
    }
}
