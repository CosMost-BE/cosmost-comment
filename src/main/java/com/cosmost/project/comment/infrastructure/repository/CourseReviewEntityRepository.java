package com.cosmost.project.comment.infrastructure.repository;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseReviewEntityRepository extends JpaRepository<CourseReviewEntity, Long> {

    Slice<CourseReviewEntity> findAllByReviewerId(Long reviewerId, Pageable pageable);

    List<CourseReviewEntity> findAllByCourseId(Long courseId);

    List<CourseReviewEntity> findByReviewerIdAndCourseId(Long reviewerId, Long courseId);
    List<CourseReviewEntity> findByCourseId(Long cousreId);

    Slice<CourseReviewEntity> findByCourseId(Long cousreId, Pageable pageable);

    @Query(value = "select c from CourseReviewEntity c " +
            "group by c.courseId order by avg(c.rate) desc")
    Slice<CourseReviewEntity> CourseAverageRateSort(Pageable pageable);

    @Query(value = "select avg(c.rate) from CourseReviewEntity c " +
            "group by c.courseId order by avg(c.rate) desc")
    List<Float> CourseAverageRate(Pageable pageable);
}
