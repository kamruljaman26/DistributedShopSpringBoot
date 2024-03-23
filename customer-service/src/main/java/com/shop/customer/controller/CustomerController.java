package com.shop.customer.controller;

import com.shop.customer.dto.CustomerRequest;
import com.shop.customer.dto.CustomerResponse;
import com.shop.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.createCustomer(customerRequest);
    }

    // READ ALL
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // READ ONE
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        return customerService.updateCustomer(id, customerRequest);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
