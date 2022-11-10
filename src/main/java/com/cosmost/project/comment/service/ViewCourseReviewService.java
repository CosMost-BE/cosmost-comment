package com.cosmost.project.comment.service;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.comment.responsebody.ReadCourseAverageRateAllResponse;
import com.cosmost.project.comment.view.ViewCourseRate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ViewCourseReviewService {

    List<ViewCourseRate> readCourseAvg(Long course);
    List<ReadCourseAverageRateAllResponse> readRankingCourseAverageRateAll(Pageable pageable);

}
