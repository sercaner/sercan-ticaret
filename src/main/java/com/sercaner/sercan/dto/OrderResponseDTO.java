package com.sercaner.sercan.dto;

import java.util.List;

public record OrderResponseDTO(
        Long id,
        Long userId,
        List<OrderItemResponseDTO> items,
        Double totalPrice,
        String createdAt,
        String updatedAt
) {}
