package com.example.sebastian_301241956_assignment2.register;

import com.example.sebastian_301241956_assignment2.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Customer, Integer> {
    // Nothing for now
}
