package com.hcmute.be_g2.controller;

import com.hcmute.be_g2.dto.CateRequestDTO;
import com.hcmute.be_g2.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    @Autowired
    private CateService cateService;
    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(cateService.getAllCategories());
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") Integer cateId){
        return ResponseEntity.ok(cateService.getCategory(cateId));
    }
    @PostMapping("/category-add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addCategory(@RequestBody CateRequestDTO body){
        return ResponseEntity.ok(cateService.addCategory(
                body.getName(),
                body.getParentId()
        ));
    }
    @PutMapping("/category-update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateCategory(@RequestBody CateRequestDTO body){
        return ResponseEntity.ok(cateService.updateCategory(
                body.getCateId(),
                body.getName(),
                body.getParentId()
        ));
    }
    @DeleteMapping("/category-del/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delCategory(@PathVariable("id") Integer cateId){
        return ResponseEntity.ok(cateService.delCategory(cateId));
    }
}
