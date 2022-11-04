package com.cosmost.project.comment.service;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.comment.infrastructure.entity.CourseReviewStatus;
import com.cosmost.project.comment.model.CourseReview;
import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;
import com.cosmost.project.comment.requestbody.UpdateCourseReviewRequest;
import com.cosmost.project.comment.responsebody.ReadMyCourseReviewsResponse;
import com.cosmost.project.comment.view.CourseDetailReviewView;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseReviewService {

    // create, read, update, delete
    CourseReview createCourseReviews(CreateCourseReviewRequest createCourseReviewRequest);
    List<ReadMyCourseReviewsResponse> readMyCourseReviews(Pageable pageable);
    List<CourseDetailReviewView> readCourseDetailReviews(Pageable pageable);
    void updateCourseReviews(Long id, UpdateCourseReviewRequest updateCourseReviewRequest);
    void deleteCourseReview(Long id);

}
