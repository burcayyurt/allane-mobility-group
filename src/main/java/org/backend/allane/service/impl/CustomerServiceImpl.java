package org.backend.allane.service.impl;

import org.backend.allane.entity.Customer;
import org.backend.allane.repository.CustomerRepository;
import org.backend.allane.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createOrEditCustomer(Customer customer) {

       return customerRepository.save(customer);

    }
    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.getById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {

        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(Long.valueOf(id));
        updatedCustomer.setFirstName(customer.getFirstName());
        updatedCustomer.setLastName(customer.getLastName());
        updatedCustomer.setBirthDate(customer.getBirthDate());

        return customerRepository.save(updatedCustomer);
    }

}
