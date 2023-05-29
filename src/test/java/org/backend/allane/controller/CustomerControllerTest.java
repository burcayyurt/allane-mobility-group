package org.backend.allane.controller;

import org.backend.allane.entity.Customer;
import org.backend.allane.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCustomer_ValidCustomer_ReturnsSavedCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Burcay");
        customer.setLastName("Yurt");
        customer.setBirthDate(new Date());

        when(customerService.createOrEditCustomer(customer)).thenReturn(customer);

        ResponseEntity<?> response = customerController.saveCustomer(customer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
        verify(customerService, times(1)).createOrEditCustomer(customer);
    }

    @Test
    void saveCustomer_InvalidCustomer_ReturnsBadRequest() {
        Customer customer = new Customer();
        customer.setId(1L);

        ResponseEntity<?> response = customerController.saveCustomer(customer);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(customerService, never()).createOrEditCustomer(customer);
    }

    @Test
    void getCustomer_ValidId_ReturnsCustomer() {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName("Burcay");
        customer.setLastName("Yurt");
        customer.setBirthDate(new Date());

        when(customerService.getCustomer(customerId)).thenReturn(customer);

        Customer result = customerController.getCustomerById(customerId);

        assertEquals(customer, result);
        verify(customerService, times(1)).getCustomer(customerId);
    }

    @Test
    void getAllCustomers_ReturnsListOfCustomers() {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setBirthDate(new Date());
        customers.add(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Jane");
        customer2.setLastName("Smith");
        customer2.setBirthDate(new Date());
        customers.add(customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        List<Customer> result = customerController.getAllCustomers();

        assertEquals(customers.size(), result.size());
        assertEquals(customers.get(0), result.get(0));
        assertEquals(customers.get(1), result.get(1));
        verify(customerService, times(1)).getAllCustomers();
    }
}
