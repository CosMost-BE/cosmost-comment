package com.cosmost.project.comment.service;

import com.cosmost.project.comment.exception.CourseNotFoundException;
import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.comment.infrastructure.repository.CourseReviewEntityRepository;
import com.cosmost.project.comment.model.CourseReview;
import com.cosmost.project.comment.responsebody.ReadCourseAverageRateAllResponse;
import com.cosmost.project.comment.view.ViewCourseRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ViewCourseReviewServiceImpl implements ViewCourseReviewService {

    private final CourseReviewEntityRepository courseReviewEntityRepository;

    @Autowired
    public ViewCourseReviewServiceImpl(CourseReviewEntityRepository courseReviewEntityRepository) {
        this.courseReviewEntityRepository = courseReviewEntityRepository;
    }


    @Override
    public List<ViewCourseRate> readCourseAvg(Long courseId) {
        double totalRate = 0;
        int totalPerson;
        float avg = 0;

        List<CourseReviewEntity> reviewEntityList = courseReviewEntityRepository.findAllByCourseId(courseId);
        List<ViewCourseRate> readViewCourseRate = new ArrayList<>();

        List<CourseReviewEntity> courseReviewEntityList = courseReviewEntityRepository.findByCourseId(courseId);

        courseReviewEntityList.stream().map(courseReviewEntity ->
                new CourseReview(courseReviewEntity)).collect(Collectors.toList()
        );

        for (int i = 0; i < courseReviewEntityList.size(); i++) {
            totalRate += courseReviewEntityList.get(i).getRate();
            totalPerson = courseReviewEntityList.size();

            avg = (float) (Math.floor((totalRate / totalPerson)*10) / 10.0);

        }

        try {
            readViewCourseRate.add(ViewCourseRate.builder()
                    .courseId(reviewEntityList.get(0).getCourseId())
                    .CourseAvgRate(avg)
                    .build());
            return readViewCourseRate;
        } catch (Exception e) {
            throw new CourseNotFoundException();
        }
    }

    @Override
    public List<ReadCourseAverageRateAllResponse> readRankingCourseAverageRateAll(Pageable pageable) {

        Slice<Long> courseReviewEntitySlice = courseReviewEntityRepository.CourseAverageRateSortId(pageable);
        List<ReadCourseAverageRateAllResponse> courseAverageRateList = new ArrayList<>();

        for(int i=0; i<courseReviewEntitySlice.getContent().size(); i++) {

            double courseAvgRate = courseReviewEntityRepository.CourseIdAverageRate(courseReviewEntitySlice.getContent().get(i).longValue());


            courseAverageRateList.add(ReadCourseAverageRateAllResponse.builder()
                    .courseId(courseReviewEntitySlice.getContent().get(i).longValue())
                    .CourseAvgRate(Math.round(courseAvgRate*10)/10.0)
                    .whetherLastPage(courseReviewEntitySlice.isLast())
                    .build());
        }



        return courseAverageRateList;
    }
}
