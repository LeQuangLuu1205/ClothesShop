package com.luu.BackEnd.service;

import com.luu.BackEnd.dto.ProductDto;
import com.luu.BackEnd.entity.Category;

import java.util.Optional;

public interface ProductService {
    boolean saveProduct(ProductDto productDto);
    Optional<Category> findById(int id);
    boolean updateProduct(ProductDto productDto);
}
