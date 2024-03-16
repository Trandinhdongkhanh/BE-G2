package com.hcmute.be_g2.controller;

import com.hcmute.be_g2.dto.LoginRequestDTO;
import com.hcmute.be_g2.dto.LoginResponseDTO;
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
    @PostMapping("/user/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO body){
        return userService.loginUser(body.getUsername(), body.getPassword());
    }
    @GetMapping("/info")
    public Principal authInfo(Principal principal){
        return principal;
    }
    @GetMapping("/user")
    public String user(){
        return "User access level";
    }
    @GetMapping("/admin")
    public String admin(){
        return "Admin access level";
    }
}
