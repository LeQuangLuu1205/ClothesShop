package com.luu.BackEnd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "image_name", length = 255)
    private String imageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable=false, updatable=false,name = "product_id")
    private Product product;
}
