package com.arifinmn.projectapi.services;

import com.arifinmn.projectapi.entities.Customers;

import java.util.List;

public interface ICustomerService {
    void createNewCustomer(Customers customer);

    void updateCustomer(Customers customers);

    boolean removeCustomerById(Integer id);

    Customers getCustomerById(Integer id);

    List<Customers> getAllCustomer();

    Integer getTotalCustomer();

    void createBulkCustomer(List<Customers> customers);
}
