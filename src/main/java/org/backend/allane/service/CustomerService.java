package org.backend.allane.service;

import org.backend.allane.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer createOrEditCustomer(Customer customer);

    Customer getCustomer(Long id);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Long id, Customer customer);
}
