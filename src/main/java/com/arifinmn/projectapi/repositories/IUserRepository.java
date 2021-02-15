package com.arifinmn.projectapi.repositories;

import com.arifinmn.projectapi.entities.Users;

import java.util.List;

public interface IUserRepository {
    Boolean existsByUsername(String username);
    Users findByUsername(String username);
}
