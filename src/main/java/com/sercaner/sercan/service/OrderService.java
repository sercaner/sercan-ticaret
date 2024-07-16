package com.sercaner.sercan.service;

import com.sercaner.sercan.dto.OrderItemRequestDTO;
import com.sercaner.sercan.dto.OrderRequestDTO;
import com.sercaner.sercan.dto.OrderResponseDTO;
import com.sercaner.sercan.mapper.OrderMapper;
import com.sercaner.sercan.model.Order;
import com.sercaner.sercan.model.OrderItem;
import com.sercaner.sercan.model.Product;
import com.sercaner.sercan.model.User;
import com.sercaner.sercan.repository.OrderRepository;
import com.sercaner.sercan.repository.ProductRepository;
import com.sercaner.sercan.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        User user = userRepository.findById(orderRequestDTO.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Order order = new Order();
        order.setUser(user);

        List<OrderItem> orderItems = mapOrderItems(orderRequestDTO.items());
        order.setOrderItems(orderItems);
        order.setTotalPrice(orderRequestDTO.totalPrice());

        order = orderRepository.save(order);
        return OrderMapper.INSTANCE.toResponseDTO(order);
    }

    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO orderRequestDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        User user = userRepository.findById(orderRequestDTO.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        order.setUser(user);

        List<OrderItem> orderItems = mapOrderItems(orderRequestDTO.items());
        order.setOrderItems(orderItems);
        order.setTotalPrice(orderRequestDTO.totalPrice());

        order = orderRepository.save(order);
        return OrderMapper.INSTANCE.toResponseDTO(order);
    }

    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return OrderMapper.INSTANCE.toResponseDTO(order);
    }

    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderMapper.INSTANCE::toResponseDTO).collect(Collectors.toList());
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        orderRepository.delete(order);
    }

    private List<OrderItem> mapOrderItems(List<OrderItemRequestDTO> orderItemRequestDTOList) {
        return orderItemRequestDTOList.stream()
                .map(item -> {
                    Product product = productRepository.findById(item.productId())
                            .orElseThrow(() -> new EntityNotFoundException("Product not found"));
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(product);
                    orderItem.setQuantity(item.quantity());
                    return orderItem;
                })
                .collect(Collectors.toList());
    }
}
