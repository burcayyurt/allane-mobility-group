package org.backend.allane.service;

import org.backend.allane.entity.Customer;
import org.backend.allane.repository.CustomerRepository;
import org.backend.allane.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrEditCustomer_ValidCustomer_ReturnsSavedCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Burcay");
        customer.setLastName("Yurt");
        customer.setBirthDate(new Date());

        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = customerService.createOrEditCustomer(customer);

        assertEquals(customer, result);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void getCustomer_ValidId_ReturnsCustomer() {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName("Burcay");
        customer.setLastName("Yurt");
        customer.setBirthDate(new Date());

        when(customerRepository.getById(customerId)).thenReturn(customer);

        Customer result = customerService.getCustomer(customerId);

        assertEquals(customer, result);
        verify(customerRepository, times(1)).getById(customerId);
    }

    @Test
    void getAllCustomers_ReturnsListOfCustomers() {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Burcay");
        customer1.setLastName("Yurt");
        customer1.setBirthDate(new Date());
        customers.add(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Simge");
        customer2.setLastName("Yurt");
        customer2.setBirthDate(new Date());
        customers.add(customer2);

        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerService.getAllCustomers();

        assertEquals(customers.size(), result.size());
        assertEquals(customers.get(0), result.get(0));
        assertEquals(customers.get(1), result.get(1));
        verify(customerRepository, times(1)).findAll();
    }
}
