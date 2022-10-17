package com.cosmost.project.comment.infrastructure.entity;

import com.cosmost.project.comment.model.CourseReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "course_review")
public class CourseReviewEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long courseId;

    @NotNull
    private Long reviewerId;

    @NotNull
    private String courseReviewContent;

    @Enumerated(EnumType.STRING)
    private CourseReviewStatus courseReviewStatus;

    @NotNull
    private Integer rate;

    @Builder
    public CourseReviewEntity(Long id, Long courseId, Long reviewerId,
                              String courseReviewContent, CourseReviewStatus courseReviewStatus,
                              Integer rate) {
        this.id = id;
        this.courseId = courseId;
        this.reviewerId = reviewerId;
        this.courseReviewContent = courseReviewContent;
        this.courseReviewStatus = courseReviewStatus;
        this.rate = rate;
    }
}
