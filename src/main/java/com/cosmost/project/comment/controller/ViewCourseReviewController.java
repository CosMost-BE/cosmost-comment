package com.cosmost.project.comment.controller;

import com.cosmost.project.comment.service.ViewCourseReviewService;
import com.cosmost.project.comment.view.ViewCourseRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/view")
@Slf4j
public class ViewCourseReviewController {

    private final ViewCourseReviewService viewCourseReviewService;

    @Autowired
    public ViewCourseReviewController(ViewCourseReviewService viewCourseReviewService) {
        this.viewCourseReviewService = viewCourseReviewService;
    }


    @GetMapping("")
    public ResponseEntity<List<ViewCourseRate>> read(@RequestParam(value = "rate", required = false) String rate,
                                                     @RequestParam(value = "courseId", required = false) Long courseId) {

        log.info(String.valueOf(courseId));
//        if (rate.equals("average") && !(courseId.equals(null))) {


        return ResponseEntity.ok().body(viewCourseReviewService.readCourseAvg(courseId));
    }
}
