package com.example.sebastian_301241956_assignment2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Integer> {
    // nothing for now
}
