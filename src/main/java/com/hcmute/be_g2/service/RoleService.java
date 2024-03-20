package com.hcmute.be_g2.service;

import com.hcmute.be_g2.entity.Role;

import java.util.List;

public interface RoleService {
    void createDefaultRole();
    List<Role> getAllRoles();
}
