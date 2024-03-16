package com.hcmute.be_g2.service;

import com.hcmute.be_g2.dto.LoginResponseDTO;
import com.hcmute.be_g2.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    List<AppUser> getAllUsers();
    AppUser register(AppUser appUser);
    LoginResponseDTO loginUser(String username, String password);
    LoginResponseDTO loginUserWithoutUsingJWT(String username, String password);
}
