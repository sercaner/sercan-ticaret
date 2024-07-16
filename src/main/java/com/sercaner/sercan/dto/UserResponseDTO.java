package com.sercaner.sercan.dto;

public record UserResponseDTO(
        Long id,
        String username,
        String email,
        String createdAt,
        String updatedAt
) {}
