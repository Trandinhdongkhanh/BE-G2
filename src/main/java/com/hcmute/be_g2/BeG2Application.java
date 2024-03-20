package com.hcmute.be_g2;

import com.hcmute.be_g2.entity.AppUser;
import com.hcmute.be_g2.enums.AppRole;
import com.hcmute.be_g2.repository.RoleRepo;
import com.hcmute.be_g2.repository.UserRepo;
import com.hcmute.be_g2.service.PermissionService;
import com.hcmute.be_g2.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.hcmute.be_g2.enums.AppRole.ADMIN;



@SpringBootApplication
public class BeG2Application {

    public static void main(String[] args) {
        SpringApplication.run(BeG2Application.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner run(UserRepo userRepo,
                          RoleRepo roleRepo,
                          PermissionService permissionService,
                          RoleService roleService,
                          PasswordEncoder passwordEncoder) {
        return args -> {
            permissionService.createDefaultPermission();
            roleService.createDefaultRole();

            if (userRepo.findAll().isEmpty()){
                AppUser appUser = new AppUser();
                appUser.setRole(roleRepo.findByAppRole(ADMIN));
                appUser.setEmail("admin@gmail.com");
                appUser.setUsername("admin");
                appUser.setPassword(passwordEncoder.encode("password"));
                userRepo.save(appUser);
            }
        };
    }
}
