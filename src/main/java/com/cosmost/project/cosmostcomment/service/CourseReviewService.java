package com.cosmost.project.cosmostcomment.service;

import com.cosmost.project.cosmostcomment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.cosmostcomment.infrastructure.entity.CourseReviewStatus;
import com.cosmost.project.cosmostcomment.requestbody.CreateCourseReviewRequest;

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
