package com.luu.BackEnd.reponsitory;

import com.luu.BackEnd.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(String roleName);
}
