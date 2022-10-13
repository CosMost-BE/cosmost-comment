package com.cosmost.project.comment.service;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.comment.infrastructure.entity.CourseReviewStatus;
import com.cosmost.project.comment.model.CourseReview;
import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;
import com.cosmost.project.comment.requestbody.UpdateCourseReviewRequest;
import com.cosmost.project.comment.view.CourseReviewView;

import java.util.List;

public interface CourseReviewService {

    // create, read, update, delete
    Long createCourseReviews(CreateCourseReviewRequest createCourseReviewRequest);
    List<CourseReviewView> readMyCourseReviews();
    List<CourseReviewView> readCourseDetailReviews();
    void updateCourseReviews(Long id, UpdateCourseReviewRequest updateCourseReviewRequest);
    void deleteCourseReview(Long id);

    default CourseReviewEntity dtoToEntity(CreateCourseReviewRequest createCourseReviewRequest){

        CourseReviewEntity courseReview = CourseReviewEntity.builder()
            .courseId(createCourseReviewRequest.getCourseId())
            .reviewerId(createCourseReviewRequest.getReviewerId())
            .courseReviewContent(createCourseReviewRequest.getCourseReviewContent())
            .rate(createCourseReviewRequest.getRate())
            .courseReviewStatus(CourseReviewStatus.ACTIVE)
            .build();

        return courseReview;
    }
}
