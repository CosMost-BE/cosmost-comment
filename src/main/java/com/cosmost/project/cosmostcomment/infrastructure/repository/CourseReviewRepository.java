package com.cosmost.project.cosmostcomment.infrastructure.repository;

import com.cosmost.project.cosmostcomment.infrastructure.entity.CourseReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseReviewRepository extends JpaRepository<CourseReviewEntity, Long> {
}
