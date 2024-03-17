package com.hcmute.be_g2.service;

import com.hcmute.be_g2.dto.LoginResponseDTO;
import com.hcmute.be_g2.entity.AppUser;

public interface UserService {
    void register(AppUser appUser);
    LoginResponseDTO loginUsingOauth2ResourceServer(String username, String password);
    LoginResponseDTO loginUsingNormalAuthentication(String username, String password);
}
