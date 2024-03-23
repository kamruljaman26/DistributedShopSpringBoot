package com.shop.customer.service;

import com.shop.customer.dto.CustomerRequest;
import com.shop.customer.dto.CustomerResponse;
import com.shop.customer.model.Customer;
import com.shop.customer.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Create a new customer
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .passwordHash(createHashPassword(request.getPassword()))
                .build();

        customer = customerRepository.save(customer);
        log.info("Customer created with ID: {}", customer.getId());
        return mapToDto(customer);
    }

    // todo: security update
    private String createHashPassword(String password) {
        return password;
    }

    // Get a specific customer by ID
    public CustomerResponse getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));
        return mapToDto(customer);
    }

    // Get all customers
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Update a customer
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPasswordHash(createHashPassword(request.getPassword()));

        customer = customerRepository.save(customer);
        log.info("Customer updated with ID: {}", customer.getId());
        return mapToDto(customer);
    }

    // Delete a customer
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found with ID: " + id);
        }
        customerRepository.deleteById(id);
        log.info("Customer deleted with ID: {}", id);
    }

    // Convert a Customer entity to a DTO
    private CustomerResponse mapToDto(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build();
    }

    public CustomerResponse getCustomerById(Long id) {
        Customer byId = customerRepository.findById(id).get();

        return CustomerResponse.builder()
                .email(byId.getEmail())
                .firstName(byId.getFirstName())
                .lastName(byId.getLastName())
                .id(byId.getId())
                .build();
    }
}
