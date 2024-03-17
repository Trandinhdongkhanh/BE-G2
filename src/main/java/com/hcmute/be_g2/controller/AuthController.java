package com.hcmute.be_g2.controller;

import com.hcmute.be_g2.dto.LoginRequestDTO;
import com.hcmute.be_g2.dto.LoginResponseDTO;
import com.hcmute.be_g2.entity.AppUser;
import com.hcmute.be_g2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser appUser) {
        userService.register(appUser);
        return ResponseEntity.ok("Register Success");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {
        LoginResponseDTO res = userService.loginUsingNormalAuthentication(body.getUsername(), body.getPassword());
        if (res.getHttpStatus() == HttpStatus.UNAUTHORIZED) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/info")
    public ResponseEntity<Authentication> authInfo() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
    }
}
