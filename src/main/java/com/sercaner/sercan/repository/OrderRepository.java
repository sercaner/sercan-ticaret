package com.sercaner.sercan.repository;

import com.sercaner.sercan.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
