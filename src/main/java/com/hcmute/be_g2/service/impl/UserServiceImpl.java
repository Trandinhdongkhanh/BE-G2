package com.hcmute.be_g2.service.impl;

import com.hcmute.be_g2.config.JwtService;
import com.hcmute.be_g2.dto.LoginResponseDTO;
import com.hcmute.be_g2.entity.AppUser;
import com.hcmute.be_g2.entity.Role;
import com.hcmute.be_g2.repository.RoleRepo;
import com.hcmute.be_g2.repository.UserRepo;
import com.hcmute.be_g2.config.TokenService;
import com.hcmute.be_g2.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private JwtService jwtService;

    @Transactional
    @Override
    public void register(AppUser appUser) {
        Set<Role> authorities = new HashSet<>();
        roleRepo.findByAuthority(USER)
                .ifPresent(authority -> authorities.add(authority));
        appUser.setAuthorities(authorities);
        appUser.setPassword(encoder.encode(appUser.getPassword()));
        userRepo.save(appUser);
    }

    @Override
    public LoginResponseDTO loginUsingOauth2ResourceServer(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(token, HttpStatus.OK, "Token Generated");
        } catch (AuthenticationException e) {
            return new LoginResponseDTO("", HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @Override
    public LoginResponseDTO loginUsingNormalAuthentication(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, password
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(userRepo.findByUsername(username).get());
        return new LoginResponseDTO(token, HttpStatus.OK, "Token generated");
    }
}
