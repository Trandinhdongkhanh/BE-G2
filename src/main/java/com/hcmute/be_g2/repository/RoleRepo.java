package com.hcmute.be_g2.repository;

import com.hcmute.be_g2.entity.Role;
import com.hcmute.be_g2.enums.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role findByAppRole(AppRole appRole);
}
