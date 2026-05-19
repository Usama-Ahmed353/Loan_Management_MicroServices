package com.example.customerservice.service;

import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repo;

    public Customer createCustomer(Customer customer) {
        if (repo.existsByCnic(customer.getCnic())) {
            throw new RuntimeException("CNIC already registered: " + customer.getCnic());
        }
        customer.setCreatedAt(LocalDateTime.now());
        return repo.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    public Customer getCustomerById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer nahi mila: " + id));
    }

    public Customer updateCustomer(Long id, Customer updated) {
        Customer existing = getCustomerById(id);
        existing.setName(updated.getName());
        existing.setPhone(updated.getPhone());
        existing.setAddress(updated.getAddress());
        return repo.save(existing);
    }
}