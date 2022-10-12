package com.cosmost.project.comment.service;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.comment.infrastructure.repository.CourseReviewEntityRepository;
import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CourseReviewServiceImpl implements CourseReviewService {

    private final CourseReviewEntityRepository courseReviewEntityRepository;

    @Autowired
    public CourseReviewServiceImpl(CourseReviewEntityRepository courseReviewRepository) {
        this.courseReviewEntityRepository = courseReviewRepository;
    }

    @Override
    public Long createCourseReviews(CreateCourseReviewRequest createCourseReviewRequest) {
        CourseReviewEntity courseReview = dtoToEntity(createCourseReviewRequest);
        courseReviewEntityRepository.save(courseReview);
        return courseReview.getCourseId();
    }

}
