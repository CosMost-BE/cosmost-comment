package com.cosmost.project.comment.exception;

import java.util.NoSuchElementException;

public class CourseReviewIdNotFoundException extends NoSuchElementException {

    private static final String MESSAGE = "코스리뷰 ID를 찾을 수 없습니다.";

    public  CourseReviewIdNotFoundException() {
        super(MESSAGE);
    }

}
