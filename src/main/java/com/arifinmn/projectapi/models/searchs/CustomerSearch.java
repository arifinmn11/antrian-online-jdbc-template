package com.arifinmn.projectapi.models.searchs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSearch {
    private Integer id;

    private String name = "";

    private String phone  = "";

    private String email  = "";

    private String service  = "";
}
