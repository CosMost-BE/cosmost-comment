package com.cosmost.project.comment.view;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.comment.infrastructure.entity.CourseReviewStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseReviewView {

    private final Long id;
    private final LocalDate createdAt;
    private final Long courseId;
    private final Long reviewerId;
    private final CourseReviewStatus courseReviewStatus;
    private final String courseReviewContent;
    private final Integer rate;

}
