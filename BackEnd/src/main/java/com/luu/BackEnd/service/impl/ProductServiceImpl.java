package com.luu.BackEnd.service.impl;

import com.luu.BackEnd.dto.ProductDto;
import com.luu.BackEnd.entity.Category;
import com.luu.BackEnd.entity.Image;
import com.luu.BackEnd.entity.Product;
import com.luu.BackEnd.entity.User;
import com.luu.BackEnd.reponsitory.CategoryRepository;
import com.luu.BackEnd.reponsitory.ImageRepository;
import com.luu.BackEnd.reponsitory.ProductRepository;
import com.luu.BackEnd.reponsitory.UserRepository;
import com.luu.BackEnd.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public boolean createProduct(ProductDto productDto) {
        try {
            Category category = categoryRepository.findById(productDto.getCatId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found"));


            User staff = userRepository.findById(productDto.getStaffId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));


            // Tạo sản phẩm mới
            Product product = new Product();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDiscount(productDto.getDiscount());
            product.setQuantity(productDto.getQuantity());
            product.setDescription(productDto.getDescription());
            product.setCategory(category);
            product.setUser(staff);

            // Lưu sản phẩm vào cơ sở dữ liệu
            product = productRepository.save(product);

            // Thêm ảnh nếu có
            if (productDto.getImageNames() != null) {
                for (String imageName : productDto.getImageNames()) {
                    Image image = new Image();
                    image.setProduct(product);
                    image.setImageName(imageName);
                    imageRepository.save(image);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public boolean updateProduct(ProductDto productDto) {
        return false;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }

    @Override
    public ProductDto getProductById(Long productId) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return null;
    }

    @Override
    public List<ProductDto> searchProductsByName(String productName) {
        return null;
    }

    @Override
    public List<ProductDto> getProductsByCategory(String category) {
        return null;
    }
}
