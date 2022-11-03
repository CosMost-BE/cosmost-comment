package com.cosmost.project.comment.responsebody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadCourseAverageRateAllResponse {

    private Long courseId;
    private double CourseAvgRate;
    private boolean whetherLastPage;

}
