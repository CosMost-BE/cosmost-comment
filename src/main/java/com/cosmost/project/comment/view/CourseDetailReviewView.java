package com.cosmost.project.comment.view;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class CourseDetailReviewView {

    private Long courseId;
    private Long courseReviewCnt;

    float[] rateAllTypeList;
    List<CourseReviewEntity> courseReviewList;

}
