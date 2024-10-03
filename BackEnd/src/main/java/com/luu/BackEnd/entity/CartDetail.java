package com.luu.BackEnd.entity;

import com.luu.BackEnd.entity.keys.CartDetailId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetail {

    @EmbeddedId
    private CartDetailId cartDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;
}
