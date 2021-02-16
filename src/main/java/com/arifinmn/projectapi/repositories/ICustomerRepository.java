package com.arifinmn.projectapi.repositories;

import com.arifinmn.projectapi.entities.Customers;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerRepository {
    void save(Customers customer);

    boolean removeById(Integer id);

    Customers findById(Integer id);

    List<Customers> findAll();

    Integer count();

}
