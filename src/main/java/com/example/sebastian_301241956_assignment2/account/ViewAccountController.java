package com.example.sebastian_301241956_assignment2.account;

import com.example.sebastian_301241956_assignment2.customer.Customer;
import com.example.sebastian_301241956_assignment2.customer.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ViewAccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/accounts/view")
    public String viewAccounts(Model model, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            List<Account> accounts = accountRepository.findByCustomer(customer);
            model.addAttribute("accounts", accounts);
        }

        return "view-accounts";
    }
    // Logic for depositing money
    @PostMapping("/accounts/deposit")
    public String deposit(@RequestParam("accountNumber") int accountNumber,
                          @RequestParam("amount") BigDecimal amount) {
        Account account = accountRepository.findById(accountNumber).orElse(null);

        if (account != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            account.setBalance(account.getBalance().add(amount));
            accountRepository.save(account);
        }

        return "redirect:/accounts/view";
    }
    // Logic for withdrawing money
    @PostMapping("/accounts/withdraw")
    public String withdraw(@RequestParam("accountNumber") int accountNumber,
                           @RequestParam("amount") BigDecimal amount) {
        Account account = accountRepository.findById(accountNumber).orElse(null);

        if (account != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            if (account.getBalance().compareTo(amount) >= 0) {
                account.setBalance(account.getBalance().subtract(amount));
                accountRepository.save(account);
            }
        }
        return "redirect:/accounts/view";
    }
    @PostMapping("accounts/delete")
    public String delete(@RequestParam("accountNumber") int accountNumber, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        Account account = accountRepository.findById(accountNumber).orElse(null);
        if (account != null && account.getCustomer().getCustomerId() == customerId) {
            accountRepository.delete(account);
        }
        return "redirect:/accounts/view";

    }
}