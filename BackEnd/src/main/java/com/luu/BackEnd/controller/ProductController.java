package com.luu.BackEnd.controller;

import com.luu.BackEnd.dto.ProductDto;
import com.luu.BackEnd.entity.Product;
import com.luu.BackEnd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
        boolean createdProduct = productService.saveProduct(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
}
