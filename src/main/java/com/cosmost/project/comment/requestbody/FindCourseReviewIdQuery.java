package com.cosmost.project.comment.requestbody;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@ToString
public class FindCourseReviewIdQuery {
    private Long reviewerId;

    public FindCourseReviewIdQuery(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

}
