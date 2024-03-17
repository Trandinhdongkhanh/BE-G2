package com.hcmute.be_g2.service.impl;

import com.hcmute.be_g2.entity.Category;
import com.hcmute.be_g2.exception.CategoryException;
import com.hcmute.be_g2.repository.CateRepo;
import com.hcmute.be_g2.service.CateService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CateServiceImpl implements CateService {
    @Autowired
    private CateRepo cateRepo;

    @Override
    public List<Category> getAllCategories() {
        return cateRepo.findAllByDeleted(false);
    }

    @Override
    public Category getCategory(Integer cateId) {
        return cateRepo.findByIdAndDeleted(cateId, false)
                .orElseThrow(() -> new CategoryException("Category with id = " + cateId + " not found"));
    }

    @Transactional
    @Override
    public Category addCategory(String name, Integer parentId) {
        Category category = new Category();
        category.setName(name);
        if (parentId == null) {
            category.setParentCategory(null);
            return cateRepo.save(category);
        }
        Category parentCategory = cateRepo.findById(parentId)
                .orElseThrow(() -> new CategoryException("Parent category with id = " + parentId + " not found"));
        category.setParentCategory(parentCategory);
        return cateRepo.save(category);
    }

    @Transactional
    @Override
    public Category delCategory(Integer cateId) {
        Optional<Category> category = cateRepo.findById(cateId);
        if (category.isPresent()) {
            category.get().setDeleted(true);
            return category.get();
        }
        throw new CategoryException("Category with id = " + cateId + " not found");
    }

    @Transactional
    @Override
    public Category updateCategory(Integer cateId, String name, Integer parentId) {
        if (cateId == null) {
            throw new CategoryException("Category ID cannot null");
        }
        Category category = cateRepo.findById(cateId)
                .orElseThrow(() -> new CategoryException("Category with id = " + cateId + " not found"));
        category.setName(name);
        if (parentId == null) {
            category.setParentCategory(null);
        } else {
            Category parentCategory = cateRepo.findById(parentId)
                    .orElseThrow(() -> new CategoryException("Parent category with id = " + parentId + " not found"));
            category.setParentCategory(parentCategory);
        }
        return category;
    }
}
