package com.example.sebastian_301241956_assignment2.login;

import com.example.sebastian_301241956_assignment2.customer.Customer;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {
    @Autowired
    private LoginRepository loginRepository;
    @GetMapping("/login")
    public String showLogin(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session, RedirectAttributes redirectAttributes) {
                        Customer customer = loginRepository.findByUsername(username);

//              if(username.isBlank()) {
//                  redirectAttributes.addFlashAttribute("error", "Enter username.");
//                  return "redirect:/login";
//              }
//              if (password.isBlank()) {
//                  redirectAttributes.addFlashAttribute("error", "Enter password.");
//                  return "redirect:/login";
//              }
            try {
                if (customer != null && customer.getPassword().equals(password)) {
                    // Gotta store the customer ID for the session
                    session.setAttribute("customerId", customer.getCustomerId());
                    return "redirect:/home";
                } else {
                    redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
                    return "redirect:/login";
                }

                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
                    return "redirect:/login";
                }

//            if (customer != null && customer.getPassword().equals(password)) {
//                // Gotta store the customer ID for the session
//                session.setAttribute("customerId", customer.getCustomerId());
//                return "redirect:/home";
//            } else {
//                return "redirect:/login";
//            }
              }
}

