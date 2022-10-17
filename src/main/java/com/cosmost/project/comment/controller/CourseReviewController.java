package com.cosmost.project.comment.controller;

import com.cosmost.project.comment.exception.CourseParamNotFoundException;
import com.cosmost.project.comment.model.CourseReview;
import com.cosmost.project.comment.requestbody.CreateCourseReviewRequest;
import com.cosmost.project.comment.requestbody.UpdateCourseReviewRequest;
import com.cosmost.project.comment.service.CourseReviewService;
import com.cosmost.project.comment.service.ReportAnswerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/comments")
public class CourseReviewController {

    private final CourseReviewService courseReviewService;
    private final ReportAnswerService reportAnswerService;


    @Autowired
    public CourseReviewController(CourseReviewService courseReviewService,
                                  ReportAnswerService reportAnswerService) {
        this.courseReviewService = courseReviewService;
        this.reportAnswerService = reportAnswerService;
    }

    // 코스리뷰 등록
    @ApiResponses({
            @ApiResponse(code = 201, message = "리뷰 등록완료 !!!!!!"),
            @ApiResponse(code = 401, message = "리뷰가 등록되지 않았습니다, 다시 확인하세요"),
            @ApiResponse(code = 403, message = "권한이 존재하지 않습니다."),
            @ApiResponse(code = 404, message = "리뷰를 찾을 수 없습니다.")
    })
    @ApiOperation(value = "코스 리뷰를 등록할 때 쓰는 메소드")
    @ApiImplicitParam(name = "course", value = "코스 리뷰를 등록한 메뉴", dataType = "CourseReviewVoReq")
    @PostMapping("")
    public ResponseEntity<String> createCourseReviews(@Valid @RequestBody CreateCourseReviewRequest createCourseReviewRequest) {

        if(createCourseReviewRequest.getType().equals("courseReview")){
            courseReviewService.createCourseReviews(createCourseReviewRequest);
            return ResponseEntity.ok("리뷰가 등록되었습니다.");
        } else if(createCourseReviewRequest.getType().equals("reportAnswer")){
            reportAnswerService.createReportAnswers(createCourseReviewRequest);
            return ResponseEntity.ok("신고답변이 등록되었습니다.");
        }
        return null;
    }


    // 코스리뷰 목록 조회(마이페이지)
    // 코스리뷰 상세페이지 조회
    @GetMapping("")
    public ResponseEntity<List<CourseReview>> readCourseReviews(@RequestParam(value = "filter", required = false) String filter,
                                                                @RequestParam(value = "type", required = false) String type) {

        if(String.valueOf(filter).equals("auth") && type.equals("review")) {
            return ResponseEntity.status(200).body(courseReviewService.readMyCourseReviews());
        } else if(type.equals("review")){
            return ResponseEntity.status(200).body(courseReviewService.readCourseDetailReviews());
        }
        throw new CourseParamNotFoundException();
    }

    // 코스리뷰 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourseReviews(@PathVariable Long id,
                                                      @Valid @RequestBody UpdateCourseReviewRequest request) {
        courseReviewService.updateCourseReviews(id,request);
        return ResponseEntity.ok("리뷰가 수정되었습니다.");
    }
    
    // 코스리뷰 삭제
    @DeleteMapping("/{id}/review")
    public ResponseEntity<String>  deleteCourseReview(@PathVariable Long id){
        courseReviewService.deleteCourseReview(id);

        return ResponseEntity.ok("리뷰가 삭제되었습니다.");
    }
}
