package com.hcmute.be_g2.config;

import com.hcmute.be_g2.entity.AppUser;
import com.hcmute.be_g2.entity.Role;
import com.hcmute.be_g2.repository.RoleRepo;
import com.hcmute.be_g2.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.hcmute.be_g2.enums.Authority.USER;

@Service
public class CustomOauth2UserService extends OidcUserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        // Delegate to the default implementation for loading a user
        OidcUser oidcUser = super.loadUser(userRequest);

        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        Set<Role> authorities = new HashSet<>();

        // TODO
        // 1) Fetch the authority information from the protected resource using accessToken
        // 2) Map the authority information to one or more GrantedAuthority's and add it to mappedAuthorities
        // 3) Create a copy of oidcUser but use the mappedAuthorities instead

        String email = oidcUser.getEmail();
        userRepo.findByEmail(email)
                .ifPresentOrElse(user -> {
                    user.getAuthorities().forEach(authority -> authorities.add((Role) authority));
                }, () -> {
                    AppUser user = new AppUser();
                    roleRepo.findByAuthority(USER).ifPresent(authority -> authorities.add(authority));
                    user.setAuthorities(authorities);
                    user.setEmail(email);
                    userRepo.save(user);
                });
        oidcUser = new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());

        return oidcUser;
    }
}
