package com.cosmost.project.cosmostcomment.service;

import com.cosmost.project.cosmostcomment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.cosmostcomment.infrastructure.repository.CourseReviewRepository;
import com.cosmost.project.cosmostcomment.requestbody.CreateCourseReviewRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CourseReviewServiceImpl implements CourseReviewService {

    private final CourseReviewRepository courseReviewRepository;

    @Autowired
    public CourseReviewServiceImpl(CourseReviewRepository courseReviewRepository) {
        this.courseReviewRepository = courseReviewRepository;
    }

    @Override
    public Long createCourseReviews(CreateCourseReviewRequest createCourseReviewRequest) {
        CourseReviewEntity courseReview = dtoToEntity(createCourseReviewRequest);
        courseReviewRepository.save(courseReview);
        return courseReview.getCourseId();
    }

}
