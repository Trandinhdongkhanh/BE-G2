package com.hcmute.be_g2.entity;

import com.hcmute.be_g2.enums.AppPermission;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "permission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Integer id;
    @Enumerated(value = EnumType.STRING)
    private AppPermission appPermission;
    public Permission(AppPermission appPermission){
        this.appPermission = appPermission;
    }
}
