package com.luu.BackEnd.reponsitory;

import com.luu.BackEnd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUserName(String email);
    User findByUserName(String userName);
}
