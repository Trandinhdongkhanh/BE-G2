package com.hcmute.be_g2.repository;

import com.hcmute.be_g2.entity.Role;
import com.hcmute.be_g2.enums.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByAuthority(Authority authority);
}
