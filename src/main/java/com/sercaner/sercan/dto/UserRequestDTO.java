package com.sercaner.sercan.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank(message = "Username is mandatory") String username,
        @NotBlank(message = "Password is mandatory")
        @Size(min = 6, max = 11, message = "Password must be at least 6 characters long") String password,
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid") String email
) {}
