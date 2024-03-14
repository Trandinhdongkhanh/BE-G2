package com.hcmute.be_g2.entity;

import com.hcmute.be_g2.enums.Authority;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;
    @Enumerated(value = EnumType.STRING)
    private Authority authority;
    @Override
    public String getAuthority() {
        return this.authority.name();
    }
    public Role(Authority authority){
        this.authority = authority;
    }
}
