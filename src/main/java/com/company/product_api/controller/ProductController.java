package com.company.product_api.controller;

import com.company.product_api.dto.Constant;
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
        JsonResponse<List<Product>> response;
        if (products.isEmpty()) {
            response = new JsonResponse<>(Constant.NO_PRODUCT_FOUND);
        } else {
            response = new JsonResponse<>(Constant.PRODUCTS_RETRIEVED);
        }
        response.setData(products);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<JsonResponse<Product>> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        JsonResponse<Product> response = new JsonResponse<>(Constant.PRODUCT_RETRIEVED);
        response.setData(product);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<JsonResponse<Product>> createProduct(@Valid @RequestBody ProductDTO product) {
        Product createdProduct = productService.createProduct(product);

        JsonResponse<Product> response = new JsonResponse<>(Constant.PRODUCT_CREATED);
        response.setData(createdProduct);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<JsonResponse<Product>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO product) {

        Product updatedProduct = productService.updateProduct(id, product);

        JsonResponse<Product> response = new JsonResponse<>(Constant.PRODUCT_UPDATED);
        response.setData(updatedProduct);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResponse<Void>> deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        JsonResponse<Void> response = new JsonResponse<>(Constant.PRODUCT_DELETED);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }
}
