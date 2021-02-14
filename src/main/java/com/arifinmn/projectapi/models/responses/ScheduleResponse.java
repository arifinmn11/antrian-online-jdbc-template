package com.arifinmn.projectapi.models.responses;

import com.arifinmn.projectapi.configs.constans.Service;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {
    private Integer id;
    private Integer customer_id;
    private String name;
    private String phone;
    private String email;
    private String status;
    private String service;

}
