package com.luu.BackEnd.service;

import com.luu.BackEnd.dto.ProductDto;
import com.luu.BackEnd.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    boolean createProduct(ProductDto productDto);
    boolean updateProduct(ProductDto productDto);
    boolean deleteProduct(Long productId);
    ProductDto getProductById(Long productId);
    List<ProductDto> getAllProducts();
    List<ProductDto> searchProductsByName(String productName);
    List<ProductDto> getProductsByCategory(String category);
}
