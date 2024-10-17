package com.luu.BackEnd.controller;

import com.luu.BackEnd.dto.ProductDto;
import com.luu.BackEnd.dto.response.ApiResponse;
import com.luu.BackEnd.dto.response.ProductResponse;
import com.luu.BackEnd.enums.ErrorCode;
import com.luu.BackEnd.exception.ApiException;
import com.luu.BackEnd.payload.response.ResponseData;
import com.luu.BackEnd.reponsitory.ProductRepository;
import com.luu.BackEnd.reponsitory.UserRepository;
import com.luu.BackEnd.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

//    @PostMapping()
//    public ResponseEntity<?> createProduct(
//            @ModelAttribute @Valid ProductDto productRequestDTO, BindingResult bindingResult) {
//        ResponseData responseData = new ResponseData();
//        if (bindingResult.hasErrors()) {
//            StringBuilder errorMessage = new StringBuilder();
//            bindingResult.getFieldErrors().forEach(error -> {
//                errorMessage.append(error.getDefaultMessage()).append("; ");
//            });
//            responseData.setSuccess(false);
//            responseData.setMessage(errorMessage.toString());
//            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
//        }
//        try {
//            boolean isCreated = productService.createProduct(productRequestDTO);
//            if (isCreated) {
//                responseData.setSuccess(true);
//                responseData.setMessage("Product created successfully!");
//                return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
//            } else {
//                responseData.setSuccess(false);
//                responseData.setMessage("Failed!");
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
//            }
//        } catch (IllegalArgumentException e) {
//            responseData.setSuccess(false);
//            responseData.setMessage(e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
//        } catch (EntityNotFoundException e) {
//            responseData.setSuccess(false);
//            responseData.setMessage(e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
//        } catch (Exception e) {
//            responseData.setSuccess(false);
//            responseData.setMessage(e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
//        }
//    }
    @PostMapping()
    public ResponseEntity<?> createProduct(
            @ModelAttribute @Valid ProductDto productRequestDTO) {

        try {
            boolean isCreated = productService.createProduct(productRequestDTO);
            if (isCreated) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse<>(1000, "Product created successfully!", null));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(1000, "Failed to create product!", null));
            }
        } catch (Exception e) {
            throw new ApiException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
}
