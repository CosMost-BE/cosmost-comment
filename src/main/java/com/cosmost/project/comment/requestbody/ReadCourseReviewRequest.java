package com.cosmost.project.comment.requestbody;

import lombok.*;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadCourseReviewRequest {

    private String filter;
    private String auth;
    private Long id;


}
