package com.arifinmn.projectapi.services.impls;

import com.arifinmn.projectapi.entities.Customers;
import com.arifinmn.projectapi.repositories.ICustomerRepository;
import com.arifinmn.projectapi.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;

    @Override
    public void createNewCustomer(Customers customer) {
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customers customers) {
        customerRepository.save(customers);
    }

    @Override
    public boolean removeCustomerById(Integer id) {
        return customerRepository.removeById(id);
    }

    @Override
    public Customers getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customers> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Integer getTotalCustomer() {
        return customerRepository.count();
    }

    @Override
    public void createBulkCustomer(List<Customers> customers) {

    }
}
