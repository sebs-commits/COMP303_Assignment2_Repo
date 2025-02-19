package com.example.sebastian_301241956_assignment2.account;

import com.example.sebastian_301241956_assignment2.account.type.AccountType;
import com.example.sebastian_301241956_assignment2.account.type.AccountTypeRepository;
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

@Controller
public class AddAccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/account/add")
    public String showAddAccountForm(Model model) {
        // Get all account types for dropdown
        model.addAttribute("accountTypes", accountTypeRepository.findAll());
        return "add-account";
    }

    @PostMapping("/account/add")
    public String addAccount(@RequestParam("accountTypeId") int accountTypeId,
                             HttpSession session) {

        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        Customer customer = customerRepository.findById(customerId).orElse(null);
        AccountType accountType = accountTypeRepository.findById(accountTypeId).orElse(null);

        if (customer != null && accountType != null) {
            Account account = new Account();
            account.setCustomer(customer);
            account.setAccountType(accountType);
            account.setBalance(new BigDecimal("0.00"));
            // If account type allows overdraft, return true. Else, set to 0
            account.setOverDraftLimit(accountType.isHasOverdraft() ? new BigDecimal("100.00") : new BigDecimal("0.00"));

            accountRepository.save(account);
        }

        return "redirect:/home";
    }
}
