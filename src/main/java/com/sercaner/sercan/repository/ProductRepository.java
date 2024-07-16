package com.sercaner.sercan.repository;

import com.sercaner.sercan.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product, Long> {
}
