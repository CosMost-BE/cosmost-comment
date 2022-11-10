package com.cosmost.project.comment.infrastructure.entity;

import com.cosmost.project.comment.model.CourseReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
                              LocalDate createdAt, LocalDate updatedAt,
                              String courseReviewContent, CourseReviewStatus courseReviewStatus,
                              Integer rate) {
        this.id = id;
        this.courseId = courseId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.reviewerId = reviewerId;
        this.courseReviewContent = courseReviewContent;
        this.courseReviewStatus = courseReviewStatus;
        this.rate = rate;
    }
}
