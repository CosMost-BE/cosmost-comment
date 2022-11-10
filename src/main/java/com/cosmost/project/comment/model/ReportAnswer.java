package com.cosmost.project.comment.model;

import com.cosmost.project.comment.infrastructure.entity.ReportAnswerEntity;
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

    public ReportAnswer(ReportAnswerEntity entity) {
        this.reportId = entity.getReportId();
        this.answerPersonId = entity.getAnswerPersonId();
        this.reportTitle = entity.getReportTitle();
        this.reportContent = entity.getReportContent();
    }

}
