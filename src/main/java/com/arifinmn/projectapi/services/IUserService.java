package com.arifinmn.projectapi.services;

import com.arifinmn.projectapi.entities.Users;

public interface IUserService {
    Users findByUsername(String username);
    Users loginGetToken(String username, String password);
}
