package com.hcmute.be_g2.service.impl;

import com.hcmute.be_g2.entity.Permission;
import com.hcmute.be_g2.entity.Role;
import com.hcmute.be_g2.repository.PermissionRepo;
import com.hcmute.be_g2.repository.RoleRepo;
import com.hcmute.be_g2.service.RoleService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hcmute.be_g2.enums.AppPermission.*;
import static com.hcmute.be_g2.enums.AppRole.*;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PermissionRepo permissionRepo;

    @Override
    @Transactional
    public void createDefaultRole() {
        if (!roleRepo.findAll().isEmpty()) return;
        try {
            Permission createProduct = permissionRepo.findByAppPermission(CREATE_PRODUCT);
            Permission readProduct = permissionRepo.findByAppPermission(READ_PRODUCT);
            Permission updateProduct = permissionRepo.findByAppPermission(UPDATE_PRODUCT);
            Permission deleteProduct = permissionRepo.findByAppPermission(DELETE_PRODUCT);

            Permission createPromotion = permissionRepo.findByAppPermission(CREATE_PROMOTION);
            Permission readPromotion = permissionRepo.findByAppPermission(READ_PROMOTION);
            Permission updatePromotion = permissionRepo.findByAppPermission(UPDATE_PROMOTION);
            Permission deletePromotion = permissionRepo.findByAppPermission(DELETE_PROMOTION);

            Permission createOrder = permissionRepo.findByAppPermission(CREATE_ORDER);
            Permission readOrder = permissionRepo.findByAppPermission(READ_ORDER);
            Permission updateOrder = permissionRepo.findByAppPermission(UPDATE_ORDER);
            Permission deleteOrder = permissionRepo.findByAppPermission(DELETE_ORDER);

            Permission createReview = permissionRepo.findByAppPermission(CREATE_REVIEW);
            Permission readReview = permissionRepo.findByAppPermission(READ_REVIEW);
            Permission updateReview = permissionRepo.findByAppPermission(UPDATE_REVIEW);
            Permission deleteReview = permissionRepo.findByAppPermission(DELETE_REVIEW);

            Permission createUser = permissionRepo.findByAppPermission(CREATE_USER);
            Permission readUser = permissionRepo.findByAppPermission(READ_USER);
            Permission updateUser = permissionRepo.findByAppPermission(UPDATE_USER);
            Permission deleteUser = permissionRepo.findByAppPermission(DELETE_USER);

            roleRepo.save(new Role(ADMIN, permissionRepo.findAll()));
            roleRepo.save(new Role(
                    CUSTOMER,
                    null    //since we don't have to distinguish between users, we only need to check the user role
            ));
            roleRepo.save(new Role(
                    SELLER_PROMOTION_ACCESS,
                    List.of(createPromotion, updatePromotion, readPromotion, deletePromotion)
            ));
            roleRepo.save(new Role(
                    SELLER_FULL_ACCESS,
                    List.of(createProduct, readProduct, updateProduct, deleteProduct,
                            createPromotion, readPromotion, updatePromotion, deletePromotion,
                            readOrder, updateOrder, deleteOrder,
                            createReview, readReview, updateReview, deleteReview,
                            createUser, readUser, updateUser, deleteUser)
            ));
            roleRepo.save(new Role(
                    SELLER_PRODUCT_ACCESS,
                    List.of(createProduct, readProduct, updateProduct, deleteProduct)
            ));
            roleRepo.save(new Role(
                    SELLER_ORDER_MANAGEMENT,
                    List.of(readOrder, updateOrder, deleteOrder)
            ));
            roleRepo.save(new Role(
                    SELLER_READ_ONLY,
                    List.of(readProduct, readPromotion, readOrder, readReview, readUser)
            ));
            roleRepo.save(new Role(
                    JUNIOR_CHAT_AGENT,
                    List.of(createReview, readReview, updateReview, deleteReview)
            ));

            LOGGER.info("Roles created");

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }
}
