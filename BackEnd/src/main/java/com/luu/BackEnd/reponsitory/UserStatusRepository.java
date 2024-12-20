package com.luu.BackEnd.reponsitory;

import com.luu.BackEnd.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {
    UserStatus findByStatusName(String statusName);
}
