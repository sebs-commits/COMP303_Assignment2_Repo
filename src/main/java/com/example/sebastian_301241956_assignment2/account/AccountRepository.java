package com.example.sebastian_301241956_assignment2.account;

import com.example.sebastian_301241956_assignment2.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByCustomer(Customer customer);
    // To find customers with savings accounts that have a balance above a certain amount
    @Query("SELECT DISTINCT c.customerName FROM Account a " +
            "JOIN a.customer c " +
            "JOIN a.accountType t " +
            "WHERE t.accountTypeId IN (1, 5, 8, 9) AND a.balance > :minBalance")
    List<String> findCustomersWithSavingsBalanceAbove(@Param("minBalance") BigDecimal minBalance);

}
