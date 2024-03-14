package com.hcmute.be_g2;

import com.hcmute.be_g2.entity.AppUser;
import com.hcmute.be_g2.entity.Role;
import com.hcmute.be_g2.repository.RoleRepo;
import com.hcmute.be_g2.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import static com.hcmute.be_g2.enums.Authority.ADMIN;
import static com.hcmute.be_g2.enums.Authority.USER;
import static com.hcmute.be_g2.enums.Authority.SELLER;

@SpringBootApplication
public class BeG2Application {

    public static void main(String[] args) {
        SpringApplication.run(BeG2Application.class, args);
    }
    @Bean
    @Transactional
    CommandLineRunner run(RoleRepo roleRepo, UserRepo userRepo, PasswordEncoder encoder){
        return args ->{
            if(!roleRepo.findAll().isEmpty()) return;
            Role adminRole = roleRepo.save(new Role(ADMIN));
            roleRepo.save(new Role(USER));
            roleRepo.save(new Role(SELLER));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            AppUser admin = new AppUser();
            admin.setEmail("admin@gmail.com");
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("password"));
            admin.setAuthorities(roles);

            userRepo.save(admin);
        };
    }
}
