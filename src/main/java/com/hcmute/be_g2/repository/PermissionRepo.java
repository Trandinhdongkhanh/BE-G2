package com.hcmute.be_g2.repository;

import com.hcmute.be_g2.entity.Permission;
import com.hcmute.be_g2.enums.AppPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PermissionRepo extends JpaRepository<Permission, Integer> {
    Permission findByAppPermission(AppPermission appPermission);
}
