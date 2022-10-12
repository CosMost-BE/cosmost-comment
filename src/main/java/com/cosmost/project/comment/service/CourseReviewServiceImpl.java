package com.cosmost.project.comment.service;

import com.cosmost.project.comment.exception.CourseReviewIdNotFoundException;
import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.comment.infrastructure.entity.CourseReviewStatus;
import com.cosmost.project.comment.infrastructure.repository.CourseReviewEntityRepository;
import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;
import com.cosmost.project.comment.requestbody.UpdateCourseReviewRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CourseReviewServiceImpl implements CourseReviewService {

    private final CourseReviewEntityRepository courseReviewEntityRepository;

    @Autowired
    public CourseReviewServiceImpl(CourseReviewEntityRepository courseReviewRepository) {
        this.courseReviewEntityRepository = courseReviewRepository;
    }

    @Override
    public Long createCourseReviews(CreateCourseReviewRequest createCourseReviewRequest) {
        CourseReviewEntity courseReview = dtoToEntity(createCourseReviewRequest);
        courseReviewEntityRepository.save(courseReview);
        return courseReview.getCourseId();
    }

    @Override
    public void updateCourseReviews(Long id, UpdateCourseReviewRequest updateCourseReviewRequest) {
        courseReviewUpdate(id, updateCourseReviewRequest);
    }

    @Override
    public void deleteCourseReview(Long id) {

        Optional<CourseReviewEntity> reviewerId =
                Optional.ofNullable(courseReviewEntityRepository.findById(id)
                        .orElseThrow(CourseReviewIdNotFoundException::new));

        if(reviewerId.isPresent()){
            courseReviewEntityRepository.deleteById(id);
        }
    }

    private CourseReviewEntity courseReviewUpdate(Long id, UpdateCourseReviewRequest updateCourseReviewRequest) {
        Optional<CourseReviewEntity> courseReview = Optional.ofNullable(courseReviewEntityRepository.findById(id)
                .orElseThrow(CourseReviewIdNotFoundException::new));

        if (courseReview.isPresent()) {
            return courseReviewEntityRepository.save(CourseReviewEntity.builder()
                    .id(id)
                    .courseId(courseReview.get().getCourseId())
                    .reviewerId(courseReview.get().getReviewerId())
                    .courseReviewContent(updateCourseReviewRequest.getCourseReviewContent())
                    .courseReviewStatus(CourseReviewStatus.ACTIVE)
                    .rate(updateCourseReviewRequest.getRate())
                    .build());
        }
        return null;
    }
}
