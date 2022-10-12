package com.cosmost.project.cosmostcomment.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private Long courseId;

    private Long reviewerId;

    private String courseReviewContent;

    @Enumerated(EnumType.STRING)
    private CourseReviewStatus courseReviewStatus;

    private Float rate;

}
