package com.cosmost.project.comment.infrastructure.repository;


import com.cosmost.project.comment.infrastructure.entity.ReportAnswerEntity;
import com.cosmost.project.comment.model.ReportAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportAnswerEntityRepository extends JpaRepository<ReportAnswerEntity, Long> {
    ReportAnswer findByReportId(Long reportId);
}
