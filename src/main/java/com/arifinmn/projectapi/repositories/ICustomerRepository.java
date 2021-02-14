package com.arifinmn.projectapi.repositories;

import com.arifinmn.projectapi.entities.Customers;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerRepository {
    boolean add(Customers customer);

    boolean update(Customers customers);

    boolean removeById(Integer id);

    Customers findById(Integer id);

    List<Customers> findAll();

    Integer count();

}
