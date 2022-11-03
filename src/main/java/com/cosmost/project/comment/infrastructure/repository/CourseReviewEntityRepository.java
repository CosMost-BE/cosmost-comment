package com.cosmost.project.comment.infrastructure.repository;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseReviewEntityRepository extends JpaRepository<CourseReviewEntity, Long> {

    List<CourseReviewEntity> findAllByReviewerId(Long reviewerId);

    List<CourseReviewEntity> findAllByCourseId(Long courseId);

    List<CourseReviewEntity> findByCourseId(Long courseId);

    List<CourseReviewEntity> findByReviewerIdAndCourseId(Long reviewerId, Long courseId);
}
