package com.luu.BackEnd.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
//    private long productId;
    @NotBlank(message = "NAME_CANNOT_BE_EMPTY")
    private String name;
    @NotNull(message = "PRICE_CANNOT_BE_NULL")
    private BigDecimal price;
    @NotNull(message = "DISCOUNT_CANNOT_BE_NULL")
    private     BigDecimal discount;
    @NotNull(message = "QUANTITY_CANNOT_BE_NULL")
    private int quantity;
    @NotBlank(message = "DESCRIPTION_CANNOT_BE_EMPTY")
    private String description;
    @NotNull(message = "CATEGORY_ID_CANNOT_BE_NULL")
    private int catId;
    private List<MultipartFile> images;
}
