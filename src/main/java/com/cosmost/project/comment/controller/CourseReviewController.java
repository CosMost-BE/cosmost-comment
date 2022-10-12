package com.cosmost.project.comment.controller;

import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;
import com.cosmost.project.comment.requestbody.FindCourseReviewIdQuery;
import com.cosmost.project.comment.requestbody.FindCourseReviewQuery;
import com.cosmost.project.comment.requestbody.UpdateCourseReviewRequest;
import com.cosmost.project.comment.service.CourseReviewService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class CourseReviewController {

    private final CourseReviewService courseReviewService;

    @Autowired
    public CourseReviewController(CourseReviewService courseReviewService) {
        this.courseReviewService = courseReviewService;
    }

    // 코스리뷰에 등록하는 api
    @ApiResponses({
            @ApiResponse(code = 201, message = "리뷰 등록완료 !!!!!!"),
            @ApiResponse(code = 401, message = "리뷰가 등록되지 않았습니다, 다시 확인하세요"),
            @ApiResponse(code = 403, message = "권한이 존재하지 않습니다."),
            @ApiResponse(code = 404, message = "리뷰를 찾을 수 없습니다.")
    })
    @ApiOperation(value = "코스 리뷰를 등록할 때 쓰는 메소드")
    @ApiImplicitParam(name = "course", value = "코스 리뷰를 등록한 메뉴", dataType = "CourseReviewVoReq")
    @PostMapping("/comments/course-reviews")
    public ResponseEntity<String> createCourseReviews(@Valid @RequestBody CreateCourseReviewRequest createCourseReviewRequest) {
        courseReviewService.createCourseReviews(createCourseReviewRequest);
        return ResponseEntity.ok("리뷰가 등록되었습니다.");
    }

    // 코스리뷰 목록 조회(코스 상세페이지)
    @GetMapping("/comments?filter=auth&type=review")
    public ResponseEntity<List<CourseReviewEntity>> readAllCourseReviews(@RequestParam(value = "filter") String filter,
                                                                         @RequestParam(value = "type") String type) {
        FindCourseReviewIdQuery findCourseReviewIdQuery= new FindCourseReviewIdQuery();
        Optional<CourseReviewEntity> id = courseReviewService.findById(findCourseReviewQuery.getId());

        if(filter.equals("auth") && type.equals("review")) {
            courseReviewService.
        }



        return courseReviewService.;
    }

    // 코스리뷰 수정
    @PutMapping("/comments/{id}")
    public ResponseEntity<String> updateCourseReviews(@PathVariable Long id,
                                                      @Valid @RequestBody UpdateCourseReviewRequest request) {
        courseReviewService.updateCourseReviews(id,request);
        return ResponseEntity.ok("리뷰가 수정되었습니다.");
    }

    @DeleteMapping("/comments/{id}/review")
    public ResponseEntity<String>  deleteCourseReview(@PathVariable Long id){
        courseReviewService.deleteCourseReview(id);
        return ResponseEntity.ok("리뷰가 삭제되었습니다.");
    }
}
