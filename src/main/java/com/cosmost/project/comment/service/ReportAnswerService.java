package com.cosmost.project.comment.service;

import com.cosmost.project.comment.infrastructure.entity.ReportAnswerEntity;
import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;

public interface ReportAnswerService {

    void createReportAnswers(CreateCourseReviewRequest createCourseReviewRequest);

    default ReportAnswerEntity reportAnswerDtoToEntity(CreateCourseReviewRequest createCourseReviewRequest){

        ReportAnswerEntity reportAnswer = ReportAnswerEntity.builder()
                .reportId(createCourseReviewRequest.getReportId())
                .answerPersonId(createCourseReviewRequest.getAnswerPersonId())
                .reportTitle(createCourseReviewRequest.getReportTitle())
                .reportContent(createCourseReviewRequest.getReportContent())
                .build();

        return reportAnswer;
    }
}
