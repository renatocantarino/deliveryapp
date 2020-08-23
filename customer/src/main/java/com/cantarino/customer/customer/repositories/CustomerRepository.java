package com.cantarino.customer.customer.repositories;

import com.cantarino.customer.customer.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>   {
}
