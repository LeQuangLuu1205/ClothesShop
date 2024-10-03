package com.luu.BackEnd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ranking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rankId;

    @Column(name = "rank_name", length = 100)
    private String rankName;

    @Column(name = "description")
    private String description; // Corresponds to description

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ranking")
    private List<User> users = new ArrayList<>();
}

