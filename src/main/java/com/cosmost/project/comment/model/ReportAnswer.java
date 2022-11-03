package com.cosmost.project.comment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class ReportAnswer {

    private Long reportId;

    private Long answerPersonId;

    private String reportTitle;

    private String reportContent;

    public ReportAnswer(ReportAnswer reportAnswer) {
        this.reportId = reportAnswer.getReportId();
        this.answerPersonId = reportAnswer.getAnswerPersonId();
        this.reportTitle = reportAnswer.getReportTitle();
        this.reportContent = reportAnswer.getReportContent();
    }

}
