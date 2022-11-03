package com.cosmost.project.comment.service;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.comment.infrastructure.entity.CourseReviewStatus;
import com.cosmost.project.comment.model.CourseReview;
import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;
import com.cosmost.project.comment.requestbody.UpdateCourseReviewRequest;
import com.cosmost.project.comment.view.CourseDetailReviewView;

import java.util.List;

public interface CourseReviewService {

    // create, read, update, delete
    CourseReview createCourseReviews(CreateCourseReviewRequest createCourseReviewRequest);
    List<CourseReview> readMyCourseReviews();
    List<CourseDetailReviewView> readCourseDetailReviews();
    void updateCourseReviews(Long id, UpdateCourseReviewRequest updateCourseReviewRequest);
    void deleteCourseReview(Long id);

}
