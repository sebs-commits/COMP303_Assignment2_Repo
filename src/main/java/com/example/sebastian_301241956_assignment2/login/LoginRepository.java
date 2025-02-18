package com.example.sebastian_301241956_assignment2.login;

import com.example.sebastian_301241956_assignment2.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository <Customer, Integer> {

    @Query(value = "SELECT * FROM customer WHERE username = :username", nativeQuery = true)
    Customer findByUsername(@Param("username") String username);
}
