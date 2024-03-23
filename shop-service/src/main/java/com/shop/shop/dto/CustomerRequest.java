package com.shop.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password; // Raw password, which will be hashed before being stored
}

