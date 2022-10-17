package com.cosmost.project.comment.service;

import com.cosmost.project.comment.infrastructure.entity.ReportAnswerEntity;
import com.cosmost.project.comment.infrastructure.repository.ReportAnswerEntityRepository;
import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReportAnswerServiceImpl implements ReportAnswerService {

    private final ReportAnswerEntityRepository reportAnswerEntityRepository;

    public ReportAnswerServiceImpl(ReportAnswerEntityRepository reportAnswerEntityRepository) {
        this.reportAnswerEntityRepository = reportAnswerEntityRepository;
    }

    @Override
    public void createReportAnswers(CreateCourseReviewRequest createCourseReviewRequest) {
        ReportAnswerEntity reportAnswer = reportAnswerDtoToEntity(createCourseReviewRequest);
        reportAnswerEntityRepository.save(reportAnswer);
    }

}
