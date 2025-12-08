package com.company.product_api.controller;

import com.company.product_api.dto.JsonResponse;
import com.company.product_api.dto.ProductDTO;
import com.company.product_api.model.Product;
import com.company.product_api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<JsonResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        JsonResponse<List<Product>> response = new JsonResponse<>("Products retrieved successfully");
        response.setData(products);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<JsonResponse<Product>> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        JsonResponse<Product> response = new JsonResponse<>("Product retrieved successfully");
        response.setData(product);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<JsonResponse<Product>> createProduct(@Valid @RequestBody ProductDTO product) {
        Product createdProduct = productService.createProduct(product);

        JsonResponse<Product> response = new JsonResponse<>("Product created successfully");
        response.setData(createdProduct);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<JsonResponse<Product>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO product) {

        Product updatedProduct = productService.updateProduct(id, product);

        JsonResponse<Product> response = new JsonResponse<>("Product updated successfully");
        response.setData(updatedProduct);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResponse<Void>> deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        JsonResponse<Void> response = new JsonResponse<>("Product deleted successfully");
        response.setSuccess(true);
        response.setData(null);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
