package com.sercaner.sercan.dto;

import jakarta.validation.constraints.NotNull;

public record OrderItemRequestDTO(
    @NotNull(message = "Product ID is mandatory") Long productId,
    @NotNull(message = "Quantity is mandatory") Integer quantity
) {}
