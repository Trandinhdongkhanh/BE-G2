package com.hcmute.be_g2.dto;

import com.hcmute.be_g2.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private AppUser appUser;
    private String jwt;
}
