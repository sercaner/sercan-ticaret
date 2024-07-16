package com.sercaner.sercan.dto;

public record OrderItemResponseDTO (
    Long id,
    Long productId,
    String productName,
    Integer quantity,
    String createdAt,
    String updatedAt
) {}
