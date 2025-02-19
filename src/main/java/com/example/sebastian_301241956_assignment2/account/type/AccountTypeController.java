package com.example.sebastian_301241956_assignment2.account.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountTypeController {
    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @GetMapping("/accountTypes")
    public String showAccountTypes(Model model) {
        List<AccountType> accountTypes = accountTypeRepository.findAll();
        model.addAttribute("accountTypes", accountTypes);
        return "account-types";
    }
}
