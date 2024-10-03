package com.luu.BackEnd.entity.keys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class OrderDetailId implements Serializable {
    private int orderId;
    private int productId;

    // Constructors, getters, and setters
}
