package com.sercaner.sercan.service;

import com.sercaner.sercan.dto.ProductRequestDTO;
import com.sercaner.sercan.dto.ProductResponseDTO;
import com.sercaner.sercan.mapper.ProductMapper;
import com.sercaner.sercan.model.Product;
import com.sercaner.sercan.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductResponseDTO createProduct(@Valid ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toEntity(productRequestDTO);
        LocalDateTime now = LocalDateTime.now();
        product.setCreatedAt(now);
        product.setUpdatedAt(now);
        product = productRepository.save(product);
        return productMapper.toResponseDTO(product);
    }

    public ProductResponseDTO updateProduct(Long id, @Valid ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        productMapper.updateEntityFromDTO(productRequestDTO, product);
        product.setUpdatedAt(LocalDateTime.now());

        product = productRepository.save(product);
        return productMapper.toResponseDTO(product);
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        return productMapper.toResponseDTO(product);
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }
}
