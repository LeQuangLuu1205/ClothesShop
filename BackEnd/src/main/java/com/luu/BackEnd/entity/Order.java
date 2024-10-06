package com.luu.BackEnd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "sum_price", precision = 10, scale = 2)
    private BigDecimal sumPrice;

    @Column(name = "discount", precision = 5, scale = 2)
    private BigDecimal discount;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false,updatable = false,name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false,updatable = false,name = "status_id")
    private OrderStatus orderStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();
}

