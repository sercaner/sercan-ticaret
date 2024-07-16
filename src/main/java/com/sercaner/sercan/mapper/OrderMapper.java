package com.sercaner.sercan.mapper;

import com.sercaner.sercan.dto.OrderItemRequestDTO;
import com.sercaner.sercan.dto.OrderItemResponseDTO;
import com.sercaner.sercan.dto.OrderRequestDTO;
import com.sercaner.sercan.dto.OrderResponseDTO;
import com.sercaner.sercan.model.Order;
import com.sercaner.sercan.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toEntity(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO toResponseDTO(Order order);

    List<OrderItem> toOrderItems(List<OrderItemRequestDTO> orderItemRequestDTOList);

    OrderItemResponseDTO toOrderItemResponseDTO(OrderItem orderItem);
}
