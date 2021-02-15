package com.arifinmn.projectapi.services.impls;

import com.arifinmn.projectapi.entities.Users;
import com.arifinmn.projectapi.repositories.IUserRepository;
import com.arifinmn.projectapi.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepository repository;

    @Override
    public Users findByUsername(String username) {
        return null;
    }

    @Override
    public Users loginGetToken(String username, String password) {
        return null;
    }
}
