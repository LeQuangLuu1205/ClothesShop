package com.luu.BackEnd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "discount")
    private double discount;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false,updatable = false,name = "cat_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false,updatable = false,name = "staff_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    List<Image> images = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    List<OrderDetail> orderDetails = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<CartDetail> cartDetails = new ArrayList<>();
}