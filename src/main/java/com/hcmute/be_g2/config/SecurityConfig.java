package com.hcmute.be_g2.config;

import com.hcmute.be_g2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private UserService userService;
    @Autowired
    private CustomOauth2UserService oauth2UserService;
    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(req -> req
                        .anyRequest().permitAll()
                )
                .authenticationProvider(provider())
                .httpBasic(Customizer.withDefaults());

//        http
//                .oauth2Login(oauth2 -> oauth2
//                        .authorizationEndpoint(auth -> auth
//                                //The url which redirect to choose a google account screen
//                                .baseUri("/login/oauth2/authorization")
//                        )
//                        .redirectionEndpoint(redirect -> redirect
//                                //The url which will be automatically called after select an google account
//                                .baseUri("/login/oauth2/code/*")
//                        )
//                        .userInfoEndpoint(userInfo -> userInfo
//                                .oidcUserService(oauth2UserService)
//                        )
//                );

        return http.build();
    }

    @Bean
    AuthenticationProvider provider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder());
        provider.setUserDetailsService(userService);
        return provider;
    }
}
