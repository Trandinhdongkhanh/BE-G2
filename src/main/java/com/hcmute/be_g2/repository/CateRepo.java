package com.hcmute.be_g2.repository;

import com.hcmute.be_g2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CateRepo extends JpaRepository<Category, Integer> {
    List<Category> findAllByDeleted(boolean deleted);
    Optional<Category> findByIdAndDeleted(Integer cateId, boolean deleted);
}
