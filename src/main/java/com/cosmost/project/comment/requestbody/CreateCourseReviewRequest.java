package com.cosmost.project.comment.requestbody;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCourseReviewRequest {

    private Long courseId;
    private CourseReviewStatus courseReviewStatus;
    private Long reviewerId;

    @Size(max = 100, message = "상품평은 ??자를 넘을 수 없습니다.")
    private String courseReviewContent;

    @Min(value = 1, message = "별점은 1이상 5이하여야 합니다.")
    @Max(value = 5, message = "별점은 1이상 5이하여야 합니다.")
    @NotNull
    private float rate;

}
