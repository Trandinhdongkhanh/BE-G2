package com.hcmute.be_g2.controller;

import com.hcmute.be_g2.entity.AppUser;
import com.hcmute.be_g2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<AppUser> register(@RequestBody AppUser appUser){
        return ResponseEntity.ok(userService.register(appUser));
    }
    @GetMapping("/user/login")
    public ResponseEntity<?> login(){
        return null;
    }
    @GetMapping("/info")
    public Principal authInfo(Principal principal){
        return principal;
    }
}
