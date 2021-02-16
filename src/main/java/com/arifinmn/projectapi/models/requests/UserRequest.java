package com.arifinmn.projectapi.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Integer id;
    private String username;
    private String password;
    private String fullName;
    private String code;
}
