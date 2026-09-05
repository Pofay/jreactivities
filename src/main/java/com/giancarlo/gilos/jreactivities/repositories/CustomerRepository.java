package com.giancarlo.gilos.jreactivities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giancarlo.gilos.jreactivities.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
