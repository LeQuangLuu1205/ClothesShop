package com.luu.BackEnd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String userName;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "fullname", nullable = false, length = 100)
    private String fullname;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role roles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private UserStatus userStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<Product> users = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    List<Order> orders = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    List<Cart> carts = new ArrayList<>();
}
