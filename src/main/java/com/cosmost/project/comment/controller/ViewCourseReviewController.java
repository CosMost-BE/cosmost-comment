package com.cosmost.project.comment.controller;

import com.cosmost.project.comment.exception.CourseNotFoundException;
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
                                                     @RequestParam(value = "course", required = false) Long course) {

        if(rate.equals("average") && !(course.equals(null))) {
            return ResponseEntity.ok().body(viewCourseReviewService.readCourseAvg(course));
        }
        return null;
    }
}
