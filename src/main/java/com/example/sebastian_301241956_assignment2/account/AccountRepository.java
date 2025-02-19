package com.example.sebastian_301241956_assignment2.account;

import com.example.sebastian_301241956_assignment2.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    //List<Account> findByCustomerId(int customerId);
    List<Account> findByCustomer(Customer customer);
}
