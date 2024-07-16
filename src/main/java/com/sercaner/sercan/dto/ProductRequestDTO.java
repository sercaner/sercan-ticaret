package com.sercaner.sercan.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequestDTO(
        @NotBlank(message = "Name is mandatory") String name,
        @Size(max = 500, message = "Description can be up to 500 characters long") String description,
        @NotNull(message = "Price is mandatory") Double price,
        @NotNull(message = "Stock is mandatory") Integer stock
) {}
