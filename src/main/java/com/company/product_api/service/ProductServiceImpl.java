package com.company.product_api.service;

import com.company.product_api.dto.Constant;
import com.company.product_api.dto.ProductDTO;
import com.company.product_api.exception.ResourceNotFoundException;
import com.company.product_api.model.Product;
import com.company.product_api.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(Constant.PRODUCT_NOT_FOUND + id)
                );
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @Override
    public Product createProduct(ProductDTO dto) {
        log.info("Creating product: {}", dto.getName());

        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setCreatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }


    @Override
    public Product updateProduct(Long id, ProductDTO dto) {
        log.info("Updating product with id {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(Constant.PRODUCT_NOT_FOUND + id)
                );

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }


    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product with id {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(Constant.PRODUCT_NOT_FOUND + id)
                );

        productRepository.delete(product);
    }
}


