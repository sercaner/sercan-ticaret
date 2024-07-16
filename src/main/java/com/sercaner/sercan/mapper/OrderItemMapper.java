package com.sercaner.sercan.mapper;

import com.sercaner.sercan.dto.OrderItemRequestDTO;
import com.sercaner.sercan.dto.OrderItemResponseDTO;
import com.sercaner.sercan.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItem toEntity(OrderItemRequestDTO orderItemRequestDTO);

    OrderItemResponseDTO toResponseDTO(OrderItem orderItem);

    void updateOrderItemFromDTO(OrderItemRequestDTO orderItemRequestDTO, @MappingTarget OrderItem orderItem);
}
