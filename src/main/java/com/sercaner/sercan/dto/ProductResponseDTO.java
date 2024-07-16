package com.sercaner.sercan.dto;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String createdAt,
        String updatedAt
) {}
