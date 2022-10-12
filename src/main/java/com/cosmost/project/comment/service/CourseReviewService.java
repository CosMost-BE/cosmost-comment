package com.cosmost.project.comment.service;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.comment.infrastructure.entity.CourseReviewStatus;
import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;

public interface CourseReviewService {

    // create, read, update, delete
    Long createCourseReviews(CreateCourseReviewRequest courseReviewRequest);

    default CourseReviewEntity dtoToEntity(CreateCourseReviewRequest createCourseReviewRequest){

        CourseReviewEntity courseReview = CourseReviewEntity.builder()
            .courseId(createCourseReviewRequest.getCourseId())
            .reviewerId(createCourseReviewRequest.getReviewerId())
            .courseReviewContent(createCourseReviewRequest.getCourseReviewContent())
            .rate(createCourseReviewRequest.getRate())
            .courseReviewStatus(CourseReviewStatus.ACTIVE)
            .build();

        return courseReview;
    };


}
