package com.cosmost.project.comment.infrastructure.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "report_answer")
public class ReportAnswerEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reportId;

    private Long answerPersonId;

    private String reportTitle;

    private String reportContent;

    @Builder
    public ReportAnswerEntity(Long id, Long reportId, Long answerPersonId, String reportTitle, String reportContent) {
        this.id = id;
        this.reportId = reportId;
        this.answerPersonId = answerPersonId;
        this.reportTitle = reportTitle;
        this.reportContent = reportContent;
    }
}
