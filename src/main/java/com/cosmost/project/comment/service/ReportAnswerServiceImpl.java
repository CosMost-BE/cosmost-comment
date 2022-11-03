package com.cosmost.project.comment.service;

import com.cosmost.project.comment.infrastructure.entity.ReportAnswerEntity;
import com.cosmost.project.comment.infrastructure.repository.ReportAnswerEntityRepository;
import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class ReportAnswerServiceImpl implements ReportAnswerService {

    @Value("${jwt.secret}")
    private String secretKey;

    private final ReportAnswerEntityRepository reportAnswerEntityRepository;

    public ReportAnswerServiceImpl(ReportAnswerEntityRepository reportAnswerEntityRepository) {
        this.reportAnswerEntityRepository = reportAnswerEntityRepository;
    }

    @Override
    public void createReportAnswers(CreateCourseReviewRequest createCourseReviewRequest) {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long answerPersonId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());


        ReportAnswerEntity reportAnswer = ReportAnswerEntity.builder()
                .reportId(createCourseReviewRequest.getReportId())
                .answerPersonId(answerPersonId)
                .reportTitle(createCourseReviewRequest.getReportTitle())
                .reportContent(createCourseReviewRequest.getReportContent())
                .build();

        reportAnswerEntityRepository.save(reportAnswer);
    }


}
