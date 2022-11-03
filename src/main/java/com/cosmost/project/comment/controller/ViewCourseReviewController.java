package com.cosmost.project.comment.controller;

import com.cosmost.project.comment.exception.CourseNotFoundException;
import com.cosmost.project.comment.exception.CourseParamNotFoundException;
import com.cosmost.project.comment.infrastructure.entity.CourseReviewEntity;
import com.cosmost.project.comment.responsebody.ReadCourseAverageRateAllResponse;
import com.cosmost.project.comment.service.ViewCourseReviewService;
import com.cosmost.project.comment.view.ViewCourseRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1")
@Slf4j
public class ViewCourseReviewController {

    private final ViewCourseReviewService viewCourseReviewService;

    @Autowired
    public ViewCourseReviewController(ViewCourseReviewService viewCourseReviewService) {
        this.viewCourseReviewService = viewCourseReviewService;
    }


    @GetMapping("/view")
    public ResponseEntity<List<ViewCourseRate>> read(@RequestParam(value = "rate", defaultValue = " ", required = false) String rate,
                                                     @RequestParam(value = "course", defaultValue = " ", required = false) Long course) {

        if(rate.equals("average") && !(course.equals(" "))) {
            return ResponseEntity.ok().body(viewCourseReviewService.readCourseAvg(course));
        }

        throw new CourseParamNotFoundException();
    }

    @GetMapping("/view/ranking")
    public ResponseEntity<List<ReadCourseAverageRateAllResponse>> readRankingCourseAverageRateAll(@RequestParam(value = "order", defaultValue = " ", required = false) String order,
                                                                                                  Pageable pageable) {

        if(order.equals("rate")) {
            return ResponseEntity.ok().body(viewCourseReviewService.readRankingCourseAverageRateAll(pageable));
        }

        throw new CourseParamNotFoundException();
    }


}
