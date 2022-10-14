package com.cosmost.project.comment.model;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.comment.infrastructure.entity.CourseReviewStatus;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CourseReview {

    private Long id;

    private Long courseId;

    private Long reviewerId;

    private String courseReviewContent;

    private CourseReviewStatus courseReviewStatus;

    private Integer rate;

    public CourseReview(CourseReviewEntity entity) {
        this.id = entity.getId();
        this.courseId = entity.getCourseId();
        this.reviewerId = entity.getReviewerId();
        this.courseReviewContent = entity.getCourseReviewContent();
        this.courseReviewStatus = entity.getCourseReviewStatus();
        this.rate = entity.getRate();
    }

}
