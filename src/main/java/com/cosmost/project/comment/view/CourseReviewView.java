package com.cosmost.project.comment.view;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewStatus;
import com.cosmost.project.comment.model.CourseReview;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseReviewView {

    private final Long id;
    private final Long courseId;
    private final Long reviewerId;
    private final CourseReviewStatus courseReviewStatus;
    private final String courseReviewContent;
    private final float rate;

    public CourseReviewView(CourseReview courseReview) {
        this.id = courseReview.getId();
        this.courseId = courseReview.getCourseId();
        this.reviewerId = courseReview.getReviewerId();
        this.courseReviewStatus = courseReview.getCourseReviewStatus();
        this.courseReviewContent = courseReview.getCourseReviewContent();
        this.rate = courseReview.getRate();
    }

}
