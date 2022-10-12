package com.cosmost.project.comment.infrastructure.repository;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseReviewEntityRepository extends JpaRepository<CourseReviewEntity, Long> {
}
