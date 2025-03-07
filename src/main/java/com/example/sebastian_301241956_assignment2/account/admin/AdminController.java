package com.example.sebastian_301241956_assignment2.account.admin;

import com.example.sebastian_301241956_assignment2.account.Account;
import com.example.sebastian_301241956_assignment2.account.AccountRepository;
import com.example.sebastian_301241956_assignment2.account.type.AccountTypeRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @GetMapping("/admin")
    public String adminDashboard(Model model, HttpSession session) {
        // Check if user is logged in
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        // get all accounts
        // create a map to store our results
        // loop through each account one by one
        // get the values we need to compare
        // check if account is bewlo minimum
        // if below, add to count
        // if we've seen the account, increase the count
        // if account is first of the type, set count to 1
        List<Account> allAccounts = accountRepository.findAll();
        Map<String, Long> accountsUnderMinBalance = new HashMap<>();

        for (Account account : allAccounts) {
            BigDecimal currentBalance = account.getBalance();
            BigDecimal minimumRequired = account.getAccountType().getMinimumBalance();
            String accountTypeName = account.getAccountType().getAccountTypeName();

            boolean isBelowMinimum = currentBalance.compareTo(minimumRequired) < 0;

            if (isBelowMinimum) {
                // if we've seen the account, increase the count
                if (accountsUnderMinBalance.containsKey(accountTypeName)) {
                    Long currentCount = accountsUnderMinBalance.get(accountTypeName);
                    accountsUnderMinBalance.put(accountTypeName, currentCount + 1);
                }
                // if account is first of the type, set count to 1
                else {
                    accountsUnderMinBalance.put(accountTypeName, 1L);
                }
            }
        }

            // List of Customer names whose savings account balance is more than $1000
            List<String> customersWithHighSavings = accountRepository
                    .findCustomersWithSavingsBalanceAbove(new BigDecimal("1000.00"));

            model.addAttribute("accountsUnderMinBalance", accountsUnderMinBalance);
            model.addAttribute("customersWithHighSavings", customersWithHighSavings);
            model.addAttribute("pageTitle", "Admin Dashboard");

            return "admin-dashboard";
        }
    }
