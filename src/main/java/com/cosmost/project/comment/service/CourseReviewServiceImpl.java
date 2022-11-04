package com.cosmost.project.comment.service;

import com.cosmost.project.comment.exception.CourseNotFoundException;
import com.cosmost.project.comment.exception.CourseReviewIdNotFoundException;
import com.cosmost.project.comment.exception.WriteReviewNotFoundException;
import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.comment.infrastructure.entity.CourseReviewStatus;
import com.cosmost.project.comment.infrastructure.repository.CourseReviewEntityRepository;
import com.cosmost.project.comment.model.CourseReview;
import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;
import com.cosmost.project.comment.requestbody.UpdateCourseReviewRequest;
import com.cosmost.project.comment.responsebody.ReadCourseDetailReviewAllResponse;
import com.cosmost.project.comment.responsebody.ReadMyCourseReviewsResponse;
import com.cosmost.project.comment.view.CourseDetailReviewView;
import io.jsonwebtoken.Jwts;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@ToString
public class CourseReviewServiceImpl implements CourseReviewService {

    @Value("${jwt.secret}")
    private String secretKey;

    private final CourseReviewEntityRepository courseReviewEntityRepository;

    @Autowired
    public CourseReviewServiceImpl(CourseReviewEntityRepository courseReviewRepository) {
        this.courseReviewEntityRepository = courseReviewRepository;
    }

    // 코스리뷰 등록
    @Override
    public CourseReview createCourseReviews(CreateCourseReviewRequest createCourseReviewRequest) {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long id = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        CourseReviewEntity courseReview = CourseReviewEntity.builder()
                .courseId(createCourseReviewRequest.getCourseId())
                .reviewerId(id)
                .courseReviewContent(createCourseReviewRequest.getCourseReviewContent())
                .rate(createCourseReviewRequest.getRate())
                .courseReviewStatus(CourseReviewStatus.ACTIVE)
                .build();

        courseReviewEntityRepository.save(courseReview);

        return CourseReview.builder()
                .id(id)
                .courseId(courseReview.getCourseId())
                .reviewerId(courseReview.getReviewerId())
                .courseReviewContent(courseReview.getCourseReviewContent())
                .courseReviewStatus(courseReview.getCourseReviewStatus())
                .rate(courseReview.getRate())
                .build();
    }

    @Override
    public List<ReadMyCourseReviewsResponse> readMyCourseReviews(Pageable pageable) {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long reviewerId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        try {
            Slice<CourseReviewEntity> reviewEntityList = (courseReviewEntityRepository.findAllByReviewerId(reviewerId, pageable));
            List<ReadMyCourseReviewsResponse> myCourseReviewsResponseList = new ArrayList<>();

            reviewEntityList.forEach(courseReviewEntity -> {
                myCourseReviewsResponseList.add(ReadMyCourseReviewsResponse.builder()
                        .id(courseReviewEntity.getId())
                        .courseId(courseReviewEntity.getCourseId())
                        .reviewerId(courseReviewEntity.getReviewerId())
                        .courseReviewContent(courseReviewEntity.getCourseReviewContent())
                        .courseReviewStatus(courseReviewEntity.getCourseReviewStatus())
                        .rate(courseReviewEntity.getRate())
                        .whetherLastPage(reviewEntityList.isLast())
                        .build());
            });

            return myCourseReviewsResponseList;

        } catch (Exception e) {
            throw new WriteReviewNotFoundException();
        }
    }

    @Override
    public List<CourseDetailReviewView> readCourseDetailReviews(Pageable pageable) {

        int totalPerson;
        double rateOneCnt = 0;
        double rateTwoCnt = 0;
        double rateThreeCnt = 0;
        double rateFourCnt = 0;
        double rateFiveCnt = 0;

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        Long courseId = Long.parseLong(request.getHeader("Authorization")); // 코스 아이디

        // 코스 아이디를 입력 받아서 찾는다.
        List<CourseReviewEntity> reviewEntityList = courseReviewEntityRepository.findAllByCourseId(courseId);
        List<CourseDetailReviewView> readPlaceDetailResponseList = new ArrayList<>();
        Slice<CourseReviewEntity> courseReviewEntitySlice = courseReviewEntityRepository.findByCourseId(courseId, pageable);
        List<CourseReviewEntity> rateAllType = courseReviewEntityRepository.findByCourseId(courseId);
        List<ReadCourseDetailReviewAllResponse> readCourseDetailReviewAllResponseList = new ArrayList<>();

        float[] rateAllTypeList = new float[5];

//        courseReviewEntitySlice.stream().map(courseReviewEntity ->
//                new CourseReview(courseReviewEntity)).collect(Collectors.toList()
//        );

        try {
            for (int i = 0; i < rateAllType.size(); i++) {
                totalPerson = rateAllType.size();

                if (rateAllType.get(i).getRate() == 1) {
                    rateOneCnt++;
                    rateAllTypeList[0] = 0;
                    rateAllTypeList[0] += (Math.round((rateOneCnt / totalPerson) * 100)) / 1.0;
                } else if (rateAllType.get(i).getRate() == 2) {
                    rateTwoCnt++;
                    rateAllTypeList[1] = 0;
                    rateAllTypeList[1] += (Math.round((rateTwoCnt / totalPerson) * 100)) / 1.0;
                } else if (rateAllType.get(i).getRate() == 3) {
                    rateThreeCnt++;
                    rateAllTypeList[2] = 0;
                    rateAllTypeList[2] += (Math.round((rateThreeCnt / totalPerson) * 100)) / 1.0;
                } else if (rateAllType.get(i).getRate() == 4) {
                    rateFourCnt++;
                    rateAllTypeList[3] = 0;
                    rateAllTypeList[3] += (Math.round((rateFourCnt / totalPerson) * 100)) / 1.0;
                } else {
                    rateFiveCnt++;
                    rateAllTypeList[4] = 0;
                    rateAllTypeList[4] += (Math.round((rateFiveCnt / totalPerson) * 100)) / 1.0;
                }
            }


            courseReviewEntitySlice.forEach(courseReviewEntity -> {
                readCourseDetailReviewAllResponseList.add(ReadCourseDetailReviewAllResponse.builder()
                        .id(courseReviewEntity.getId())
                        .reviewerId(courseReviewEntity.getReviewerId())
                        .courseReviewContent(courseReviewEntity.getCourseReviewContent())
                        .courseReviewStatus(courseReviewEntity.getCourseReviewStatus())
                        .rate(courseReviewEntity.getRate())
                        .whetherLastPage(courseReviewEntitySlice.isLast())
                        .build());
            });


            readPlaceDetailResponseList.add(
                    CourseDetailReviewView.builder()
                            .courseId(reviewEntityList.get(0).getCourseId())
                            .courseReviewCnt((long) rateAllType.size())
                            .rateAllTypeList(rateAllTypeList)
                            .courseReviewList(readCourseDetailReviewAllResponseList)
                            .build());

            return readPlaceDetailResponseList;

        } catch (Exception e) {
            throw new CourseNotFoundException();
        }

    }

    @Override
    public void updateCourseReviews(Long id, UpdateCourseReviewRequest updateCourseReviewRequest) {



        courseReviewUpdate(id, updateCourseReviewRequest);
    }

    @Override
    public void deleteCourseReview(Long courseId) {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long reviewerId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        try {
            Optional<List<CourseReviewEntity>> courseReview =
                    Optional.ofNullable(Optional.ofNullable(courseReviewEntityRepository.findByReviewerIdAndCourseId(reviewerId, courseId))
                            .orElseThrow(CourseReviewIdNotFoundException::new));

            if (courseReview.isPresent()) {
                courseReviewEntityRepository.deleteById(courseReview.get().get(0).getId());
            }
        } catch (Exception e) {
            throw new CourseReviewIdNotFoundException();
        }

    }

    private CourseReviewEntity courseReviewUpdate(Long courseId, UpdateCourseReviewRequest updateCourseReviewRequest) {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long reviewerId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        Optional<List<CourseReviewEntity>> courseReview =
                Optional.ofNullable(Optional.ofNullable(courseReviewEntityRepository.findByReviewerIdAndCourseId(reviewerId, courseId))
                        .orElseThrow(CourseReviewIdNotFoundException::new));

        if (courseReview.isPresent()) {
            return courseReviewEntityRepository.save(CourseReviewEntity.builder()
                    .id(courseReview.get().get(0).getId())
                    .courseId(courseReview.get().get(0).getCourseId())
                    .reviewerId(reviewerId)
                    .courseReviewStatus(CourseReviewStatus.ACTIVE)
                    .courseReviewContent(updateCourseReviewRequest.getCourseReviewContent())
                    .rate(updateCourseReviewRequest.getRate())
                    .build());
        }
        return null;
    }
}
