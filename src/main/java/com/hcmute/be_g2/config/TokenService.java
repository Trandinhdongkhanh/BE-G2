package com.hcmute.be_g2.config;

import com.hcmute.be_g2.entity.AppUser;
import com.hcmute.be_g2.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;


//Service used for oauth2 to generate token
@Service
public class TokenService {
    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;
    @Autowired
    private UserRepo userRepo;

    public String generateJwt(Authentication auth){
        Instant now = Instant.now();
        Optional<AppUser> appUser = userRepo.findByUsername(auth.getName());
        String email = null;
        if (appUser.isPresent()) email = appUser.get().getEmail();

        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(auth.getName())    //username of the person who login
                .claim("roles", scope)
                .claim("email", email)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
