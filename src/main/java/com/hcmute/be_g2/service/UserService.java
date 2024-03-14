package com.hcmute.be_g2.service;

import com.hcmute.be_g2.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<AppUser> getAllUsers();
    AppUser register(AppUser appUser);
}
