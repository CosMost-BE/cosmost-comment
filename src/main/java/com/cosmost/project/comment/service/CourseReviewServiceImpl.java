package com.cosmost.project.comment.service;

import com.cosmost.project.comment.exception.CourseReviewIdNotFoundException;
import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.comment.infrastructure.repository.CourseReviewEntityRepository;
import com.cosmost.project.comment.model.CourseReview;
import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;
import com.cosmost.project.comment.requestbody.UpdateCourseReviewRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<CourseReview> readMyCourseReviews() {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        Long id = Long.parseLong(request.getHeader("Authorization"));

        List<CourseReviewEntity> reviewEntityList = courseReviewEntityRepository.findAllByReviewerId(id);

        return reviewEntityList.stream().map(courseReview ->
                new CourseReview(courseReview)).collect(Collectors.toList());
    }

    @Override
    public List<CourseReview> readCourseDetailReviews() {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        Long id = Long.parseLong(request.getHeader("Authorization"));


        List<CourseReviewEntity> reviewEntityList = courseReviewEntityRepository.findAllByCourseId(id);
        return reviewEntityList.stream().map(courseReview ->
                new CourseReview(courseReview)).collect(Collectors.toList());
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
                    .courseId(updateCourseReviewRequest.getCourseId())
                    .reviewerId(courseReview.get().getReviewerId())
                    .courseReviewContent(updateCourseReviewRequest.getCourseReviewContent())
                    .rate(updateCourseReviewRequest.getRate())
                    .build());
        }
        return null;
    }
}
