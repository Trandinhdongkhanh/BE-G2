package com.hcmute.be_g2.service;

import com.hcmute.be_g2.entity.Category;

import java.util.List;

public interface CateService {
    List<Category> getAllCategories();
    Category getCategory(Integer cateId);
    Category addCategory(String name, Integer parentId);
    Category delCategory(Integer cateId);
    Category updateCategory(Integer cateId, String name, Integer parentId);
}
