package com.luu.BackEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    private int imageId; // ID của hình ảnh (tự động tăng)
    private int productId;
    private String imageName;

    public ImageDto(String imageName) {
        this.imageName = imageName;
    }
}
