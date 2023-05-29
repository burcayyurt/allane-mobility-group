package org.backend.allane.controller;

import org.backend.allane.entity.Customer;
import org.backend.allane.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id) {
       return customerService.getCustomer(id);
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        try {

            if (customer.getFirstName() == null || customer.getFirstName() == "") {
                return ResponseEntity.badRequest().body("{\"error\": \"First Name field is required!.\"}");
            }

            if (customer.getLastName() == null || customer.getLastName() == "") {
                return ResponseEntity.badRequest().body("{\"error\": \"Last Name field is required!\"}");
            }

            if (customer.getBirthDate() == null || customer.getBirthDate().equals("")) {
                return ResponseEntity.badRequest().body("{\"error\": \"Birthdate field is required!\"}");
            }

            Customer savedCustomer = customerService.createOrEditCustomer(customer);
            return ResponseEntity.ok(savedCustomer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCustomer(@PathVariable("id") String id,@RequestBody Customer customer) {
        try {
            Customer updatedCustomer = customerService.updateCustomer(Long.valueOf(id), customer);
            return ResponseEntity.ok(updatedCustomer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
