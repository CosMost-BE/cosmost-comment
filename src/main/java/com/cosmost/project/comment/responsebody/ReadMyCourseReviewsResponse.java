package com.cosmost.project.comment.responsebody;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadMyCourseReviewsResponse {

    private Long id;
    private Long courseId;
    private LocalDate createdAt;
    private Long reviewerId;
    private String courseReviewContent;
    private CourseReviewStatus courseReviewStatus;
    private Integer rate;

    private boolean whetherLastPage;

}
