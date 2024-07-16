package com.sercaner.sercan.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequestDTO (
    @NotNull(message = "User ID is mandatory") Long userId,
    @NotNull(message = "Order items are mandatory") List<OrderItemRequestDTO> items,
    @NotNull(message = "Total price is mandatory") Double totalPrice
) {}

