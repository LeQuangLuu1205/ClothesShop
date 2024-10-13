package com.luu.BackEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private long productId; // Giữ lại để trả về thông tin sản phẩm
    private String name;
    private BigDecimal price;
    private BigDecimal discount;
    private int quantity;
    private String description;
    private int catId;
    private int staffId;
    private List<String> imageNames;
}
