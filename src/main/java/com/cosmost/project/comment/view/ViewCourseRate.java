package com.cosmost.project.comment.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class ViewCourseRate {

    private Long id;
    private Long courseId;
    private float CourseAvgRate;

}
