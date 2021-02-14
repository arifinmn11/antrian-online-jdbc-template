package com.arifinmn.projectapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleModel {

    private Integer id;
    @NotNull
    private String status;
    @NotNull
    private Integer customer_id;
}
