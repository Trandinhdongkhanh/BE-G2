package com.hcmute.be_g2.service.impl;

import com.hcmute.be_g2.entity.Permission;
import com.hcmute.be_g2.repository.PermissionRepo;
import com.hcmute.be_g2.service.PermissionService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.hcmute.be_g2.enums.AppPermission.*;


@Service
public class PermissionServiceImpl implements PermissionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionServiceImpl.class);
    @Autowired
    private PermissionRepo permissionRepo;

    @Override
    @Transactional
    public void createDefaultPermission() {
        if (!permissionRepo.findAll().isEmpty()) return;
        try {
            List<Permission> permissions = defaultPermissions();
            permissionRepo.saveAll(permissions);
            LOGGER.info("Permissions created successfully");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private List<Permission> defaultPermissions() {
        List<Permission> permissions = new ArrayList<>();

        permissions.add(new Permission(CREATE_PRODUCT));
        permissions.add(new Permission(READ_PRODUCT));
        permissions.add(new Permission(UPDATE_PRODUCT));
        permissions.add(new Permission(DELETE_PRODUCT));

        permissions.add(new Permission(CREATE_ORDER));
        permissions.add(new Permission(READ_ORDER));
        permissions.add(new Permission(UPDATE_ORDER));
        permissions.add(new Permission(DELETE_ORDER));

        permissions.add(new Permission(CREATE_PROMOTION));
        permissions.add(new Permission(READ_PROMOTION));
        permissions.add(new Permission(UPDATE_PROMOTION));
        permissions.add(new Permission(DELETE_PROMOTION));

        permissions.add(new Permission(CREATE_REVIEW));
        permissions.add(new Permission(READ_REVIEW));
        permissions.add(new Permission(UPDATE_REVIEW));
        permissions.add(new Permission(DELETE_REVIEW));

        permissions.add(new Permission(CREATE_USER));
        permissions.add(new Permission(READ_USER));
        permissions.add(new Permission(UPDATE_USER));
        permissions.add(new Permission(DELETE_USER));

        permissions.add(new Permission(CREATE_CATEGORY));
        permissions.add(new Permission(READ_CATEGORY));
        permissions.add(new Permission(UPDATE_CATEGORY));
        permissions.add(new Permission(DELETE_CATEGORY));

        return permissions;
    }
}
