package com.giancarlo.gilos.jreactivities.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giancarlo.gilos.jreactivities.entities.Customer;
import com.giancarlo.gilos.jreactivities.repositories.CustomerRepository;

@RestController
public class CustomerController {

  private final CustomerRepository repo;

  public CustomerController(CustomerRepository repo) {
    this.repo = repo;
  }


  @GetMapping("/api/customers")
  public List<Customer> getAll() {
    return repo.findAll();
  }
}