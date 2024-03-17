package com.hcmute.be_g2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private String image;
    private boolean available = true;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cate_id")
    private Category category;
}
