package com.arifinmn.projectapi.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Integer id;
    @NotBlank
    @Min(value = 8)
    @Max(value = 20)
    private String username;
    @NotBlank
    @Min(value = 8)
    @Max(value = 30)
    private String password;
    @NotBlank
    private String fullName;
    private String code;
}
