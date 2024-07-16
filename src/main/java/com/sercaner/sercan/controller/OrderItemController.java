package com.sercaner.sercan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sercaner.sercan.dto.OrderItemRequestDTO;
import com.sercaner.sercan.dto.OrderItemResponseDTO;
import com.sercaner.sercan.service.OrderItemService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity<OrderItemResponseDTO> createOrderItem(@Valid @RequestBody OrderItemRequestDTO orderItemRequestDTO) {
        OrderItemResponseDTO createdOrderItem = orderItemService.createOrderItem(orderItemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponseDTO> getOrderItemById(@PathVariable Long id) {
        OrderItemResponseDTO orderItem = orderItemService.getOrderItemById(id);
        return ResponseEntity.ok(orderItem);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemResponseDTO>> getAllOrderItems() {
        List<OrderItemResponseDTO> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemResponseDTO> updateOrderItem(@PathVariable Long id, @Valid @RequestBody OrderItemRequestDTO orderItemRequestDTO) {
        OrderItemResponseDTO updatedOrderItem = orderItemService.updateOrderItem(id, orderItemRequestDTO);
        return ResponseEntity.ok(updatedOrderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
