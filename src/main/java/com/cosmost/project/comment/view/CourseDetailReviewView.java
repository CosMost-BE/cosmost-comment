package com.cosmost.project.comment.view;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class CourseDetailReviewView {

    private Long id;
    private Long courseId;
    private double rate;

    String[] rateAllTypeList;
    ArrayList<ArrayList<Integer>> integerList;
    List<CourseReviewEntity> courseReviewList;

    public CourseDetailReviewView(CourseReviewEntity courseReview) {
        this.id = courseReview.getId();
        this.rate = courseReview.getRate();
        this.courseId = courseReview.getCourseId();
        this.integerList = getIntegerList();
        this.rateAllTypeList = getRateAllTypeList();
        this.courseReviewList = getCourseReviewList();
    }

}
