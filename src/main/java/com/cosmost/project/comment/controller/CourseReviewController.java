package com.cosmost.project.comment.controller;

import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;
import com.cosmost.project.comment.requestbody.UpdateCourseReviewRequest;
import com.cosmost.project.comment.service.CourseReviewService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> createCourseReviews(@RequestBody CreateCourseReviewRequest createCourseReviewRequest) {
        courseReviewService.createCourseReviews(createCourseReviewRequest);
        return ResponseEntity.ok("리뷰가 등록되었습니다.");
    }

    // 코스리뷰 수정
    @PutMapping("/comments/{id}")
    public ResponseEntity<String> updateCourseReviews(@PathVariable Long id, @RequestBody UpdateCourseReviewRequest request) {
        courseReviewService.updateCourseReviews(id,request);
        return ResponseEntity.ok("리뷰가 수정되었습니다.");
    }
}
