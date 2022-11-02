package com.cosmost.project.comment.service;

import com.cosmost.project.comment.view.ViewCourseRate;

import java.util.List;

public interface ViewCourseReviewService {

    List<ViewCourseRate> readCourseAvg(Long courseId);
}
