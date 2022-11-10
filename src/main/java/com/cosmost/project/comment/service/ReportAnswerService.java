package com.cosmost.project.comment.service;

import com.cosmost.project.comment.model.ReportAnswer;
import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;

public interface ReportAnswerService {

    void createReportAnswers(CreateCourseReviewRequest createCourseReviewRequest);
    ReportAnswer readMyReport();

}
