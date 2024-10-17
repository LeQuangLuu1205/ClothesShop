package com.luu.BackEnd.service.impl;

import com.luu.BackEnd.config.CustomJwtFilter;
import com.luu.BackEnd.dto.ProductDto;
import com.luu.BackEnd.entity.Category;
import com.luu.BackEnd.entity.Image;
import com.luu.BackEnd.entity.Product;
import com.luu.BackEnd.entity.User;
import com.luu.BackEnd.enums.ErrorCode;
import com.luu.BackEnd.exception.ApiException;
import com.luu.BackEnd.reponsitory.CategoryRepository;
import com.luu.BackEnd.reponsitory.ImageRepository;
import com.luu.BackEnd.reponsitory.ProductRepository;
import com.luu.BackEnd.reponsitory.UserRepository;
import com.luu.BackEnd.service.FileService;
import com.luu.BackEnd.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private CustomJwtFilter customJwtFilter;
    private Category getCategory(int catId) {
        return categoryRepository.findById(catId)
                .orElseThrow(() -> new ApiException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    private User getStaff(String username) {
        User staff = userRepository.findByUserName(username);
        if (staff == null) {
            throw new ApiException(ErrorCode.USER_NOT_FOUND);
        }
        return staff;
    }
    @Override
    public boolean createProduct(ProductDto productDto) {
        Category category = getCategory(productDto.getCatId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User staff = getStaff(username);
        // Tạo sản phẩm mới
        Product product = buildProductFromDto(productDto, category, staff);
        try {
            product = productRepository.save(product);
            if (productDto.getImages()!=null)
                saveProductImages(productDto.getImages(), product);

            return true;
        } catch (ApiException  e) {
            throw e;
        }  catch (Exception e) {
            throw new ApiException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    private Product buildProductFromDto(ProductDto productDto, Category category, User staff) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDiscount(productDto.getDiscount());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);
        product.setUser(staff);
        return product;
    }

//    private void saveProductImages(List<MultipartFile> images, Product product) {
//        if (images != null && !images.isEmpty()) {
//            for (MultipartFile file : images) {
//                if (!file.isEmpty()) {
//                    boolean isSaved = fileService.saveFile(file);
//                    if (isSaved) {
//                        Image image = new Image();
//                        image.setImageName(file.getOriginalFilename());
//                        image.setProduct(product);
//                        imageRepository.save(image);
//                    } else {
//                        throw new ApiException(ErrorCode.IMAGE_SAVE_FAILED);
//                    }
//                } else {
//                    logger.warn("Skipped empty file: {}", file.getOriginalFilename());
//                }
//            }
//        }
//    }
    private void saveProductImages(List<MultipartFile> images, Product product) {
        if (images != null && !images.isEmpty()) {
            images.stream()
                    .filter(file -> !file.isEmpty()) // Lọc ra những file không rỗng
                    .forEach(file -> {
                        boolean isSaved = fileService.saveFile(file); // Lưu file
                        if (isSaved) {
                            Image image = new Image();
                            image.setImageName(file.getOriginalFilename()); // Lấy tên gốc của file
                            image.setProduct(product); // Gán sản phẩm cho ảnh
                            imageRepository.save(image); // Lưu ảnh vào CSDL
                        } else {
                            logger.warn("Failed to save image: {}", file.getOriginalFilename());
                        }
                    });
        } else {
            logger.warn("No images to save or image list is empty.");
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
