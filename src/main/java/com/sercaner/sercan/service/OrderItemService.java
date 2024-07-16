package com.sercaner.sercan.service;

import com.sercaner.sercan.dto.OrderItemRequestDTO;
import com.sercaner.sercan.dto.OrderItemResponseDTO;
import com.sercaner.sercan.mapper.OrderItemMapper;
import com.sercaner.sercan.model.OrderItem;
import com.sercaner.sercan.model.Product;
import com.sercaner.sercan.repository.OrderItemRepository;
import com.sercaner.sercan.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderItemMapper orderItemMapper;

    public OrderItemService(OrderItemRepository orderItemRepository, ProductRepository productRepository, OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderItemMapper = orderItemMapper;
    }

    public OrderItemResponseDTO createOrderItem(OrderItemRequestDTO orderItemRequestDTO) {
        Product product = productRepository.findById(orderItemRequestDTO.productId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        OrderItem orderItem = orderItemMapper.toEntity(orderItemRequestDTO);
        orderItem.setProduct(product);

        orderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toResponseDTO(orderItem);
    }

    public OrderItemResponseDTO updateOrderItem(Long id, OrderItemRequestDTO orderItemRequestDTO) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order Item not found"));

        Product product = productRepository.findById(orderItemRequestDTO.productId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        orderItemMapper.updateOrderItemFromDTO(orderItemRequestDTO, orderItem);
        orderItem.setProduct(product);

        orderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toResponseDTO(orderItem);
    }

    public OrderItemResponseDTO getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order Item not found"));
        return orderItemMapper.toResponseDTO(orderItem);
    }

    public List<OrderItemResponseDTO> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream().map(orderItemMapper::toResponseDTO).collect(Collectors.toList());
    }

    public void deleteOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order Item not found"));
        orderItemRepository.delete(orderItem);
    }
}
