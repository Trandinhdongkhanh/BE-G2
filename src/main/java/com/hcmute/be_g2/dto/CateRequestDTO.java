package com.hcmute.be_g2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CateRequestDTO {
    private Integer cateId;
    private String name;
    private Integer parentId;
}
