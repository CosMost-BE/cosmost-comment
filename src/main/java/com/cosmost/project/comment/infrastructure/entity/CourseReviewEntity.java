package com.cosmost.project.comment.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

}
