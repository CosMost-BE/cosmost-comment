package com.cosmost.project.cosmostcomment.model;

import com.cosmost.project.cosmostcomment.infrastructure.entity.CourseReviewStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class CourseReview {

    private Long id;

    private Long courseId;

    private Long reviewerId;

    private String courseReviewContent;

    private CourseReviewStatus courseReviewStatus;

    private Float rate;

}
