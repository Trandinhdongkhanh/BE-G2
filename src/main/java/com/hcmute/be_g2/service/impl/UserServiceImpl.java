package com.hcmute.be_g2.service.impl;

import com.hcmute.be_g2.entity.AppUser;
import com.hcmute.be_g2.entity.Role;
import com.hcmute.be_g2.repository.RoleRepo;
import com.hcmute.be_g2.repository.UserRepo;
import com.hcmute.be_g2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.hcmute.be_g2.enums.Authority.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public List<AppUser> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public AppUser register(AppUser appUser) {
        Set<Role> authorities = new HashSet<>();
        roleRepo.findByAuthority(USER)
                .ifPresent(authority -> authorities.add(authority));
        appUser.setAuthorities(authorities);
        appUser.setPassword(encoder.encode(appUser.getPassword()));
        return userRepo.save(appUser);
    }
}
