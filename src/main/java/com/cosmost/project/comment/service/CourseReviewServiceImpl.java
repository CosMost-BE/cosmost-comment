package com.cosmost.project.comment.service;

import com.cosmost.project.comment.exception.CourseReviewIdNotFoundException;
import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.comment.infrastructure.repository.CourseReviewEntityRepository;
import com.cosmost.project.comment.model.CourseReview;
import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;
import com.cosmost.project.comment.requestbody.UpdateCourseReviewRequest;
import com.cosmost.project.comment.view.CourseDetailReviewView;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@ToString
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
    public List<CourseDetailReviewView> readCourseDetailReviews() {
        int totalPerson;
        double rateOneCnt = 0;
        double rateTwoCnt = 0;
        double rateThreeCnt = 0;
        double rateFourCnt = 0;
        double rateFiveCnt = 0;

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        Long courseId = Long.parseLong(request.getHeader("Authorization")); // 코스 아이디가 들어 옴.

        // 코스 아이디를 입력 받아서 찾는다.
        List<CourseReviewEntity> reviewEntityList = courseReviewEntityRepository.findAllByCourseId(courseId);
        List<CourseDetailReviewView> readPlaceDetailResponseList = new ArrayList<>();
        List<CourseReviewEntity> courseReviewEntityList = courseReviewEntityRepository.findByCourseId(courseId);

        float[] rateAllTypeList = new float[5];

        courseReviewEntityList.stream().map(courseReviewEntity ->
                new CourseReview(courseReviewEntity)).collect(Collectors.toList()
        );

        for (int i = 0; i < courseReviewEntityList.size(); i++) {
            totalPerson = courseReviewEntityList.size();

            if (courseReviewEntityList.get(i).getRate() == 1) {
                rateOneCnt++;
                rateAllTypeList[0] = 0;
                rateAllTypeList[0] += (Math.round((rateOneCnt / totalPerson) * 100)) / 1.0;
            } else if (courseReviewEntityList.get(i).getRate() == 2) {
                rateTwoCnt++;
                rateAllTypeList[1] = 0;
                rateAllTypeList[1] += (Math.round((rateTwoCnt / totalPerson) * 100)) / 1.0;
            } else if (courseReviewEntityList.get(i).getRate() == 3) {
                rateThreeCnt++;
                rateAllTypeList[2] = 0;
                rateAllTypeList[2] += (Math.round((rateThreeCnt / totalPerson) * 100)) / 1.0;
            } else if (courseReviewEntityList.get(i).getRate() == 4) {
                rateFourCnt++;
                rateAllTypeList[3] = 0;
                rateAllTypeList[3] += (Math.round((rateFourCnt / totalPerson) * 100)) / 1.0;
            } else {
                rateFiveCnt++;
                rateAllTypeList[4] = 0;
                rateAllTypeList[4] += (Math.round((rateFiveCnt / totalPerson) * 100)) / 1.0;
            }
        }

        readPlaceDetailResponseList.add(
                CourseDetailReviewView.builder()
                        .courseId(reviewEntityList.get(0).getCourseId())
                        .courseReviewCnt((long) courseReviewEntityList.size())
                        .rateAllTypeList(new String[]{Arrays.toString(rateAllTypeList)})
                        .courseReviewList(courseReviewEntityList)
                        .build());

        return readPlaceDetailResponseList;
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

        if (reviewerId.isPresent()) {
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
